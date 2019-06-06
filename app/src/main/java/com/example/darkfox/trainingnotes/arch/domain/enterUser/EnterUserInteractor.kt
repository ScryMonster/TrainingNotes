package com.example.darkfox.trainingnotes.arch.domain.enterUser

import android.util.Patterns
import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.errors.FirebaseLoginException
import com.example.darkfox.trainingnotes.dto.errors.FirebaseRegisterException
import com.example.darkfox.trainingnotes.dto.errors.FirestoreExceptionCreatingAccountDoc
import com.example.darkfox.trainingnotes.dto.errors.FirestoreExceptionGettingAccountDoc
import com.example.darkfox.trainingnotes.utils.extensions.checkResultSusp
import java.util.regex.Pattern

class EnterUserInteractor : IEnterUserInteractor {

    override fun checkEmail(email: String, success: () -> Unit, error: (String) -> Unit) {
        if (email.length > 8 && Patterns.EMAIL_ADDRESS.matcher(email).matches()) success()
        else checkEmailError(email, error)
    }

    override fun checkPassword(password: String, success: () -> Unit, error: (String) -> Unit) {
        if (password.length > 8 && validPassword(password)) success()
        else checkPasswordEmail(password, error)
    }

    override suspend fun signIn(email: String?, password: String?, success: (Account) -> Unit, fail: (Exception) -> Unit) {
        if (email != null && password != null) {
            //region previous version

//            firebaseAuthRepository.signIn(email, password,
//                    { firebaseUser ->
//                        val account = Account(firebaseUser.uid, firebaseUser.email)
//                        verifyAccount(account, success)
//                    },
//                    {
//                        Log.i("Firebase Auth error: ", it.toString())
//                        error.invoke(it)
//                    })
            //endregion

            DataProvider.login(email, password).checkResultSusp({ authResult ->
                val user = authResult.user
                if (user != null) {
                    val account = Account(user.uid, user.email)
                    verifyAccount(account, success,fail)
                }
            }, {
                fail(FirebaseLoginException(it.message ?: it.localizedMessage))
            })
        }
    }

    override suspend fun register(email: String?, password: String?, success: (Account) -> Unit, fail: (Exception) -> Unit) {
        if (email != null && password != null) {
            //region previous version
//            firebaseAuthRepository.register(email, password,
//                    { firebaseUser ->
//                        val account = Account(firebaseUser.uid, firebaseUser.email)
//                        accountRepository.saveToSharedPrefs(account)
//                        accountRepository.saveToDatabase(account)
//                        firestoreRepository.create(account, success = {
//                            success(account)
//                        })
//                    },
//                    {
//                        Log.i("Firebase Auth error: ", it.toString())
//                        error.invoke(it)
//                    })
            //endregion

            DataProvider.register(email, password).checkResultSusp({ authResult ->
                val user = authResult.user
                if (user != null) {
                    val account = Account(user.uid, user.email)
                    DataProvider.saveCurrentAccount(account)
                    DataProvider.saveAccount(account)
                    DataProvider.createAccountDocument(account, { success(account) }, fail)
                }
            }, {
                fail(FirebaseRegisterException(it.message ?: it.localizedMessage))
            })
        }
    }

    private suspend fun verifyAccount(account: Account, success: (Account) -> Unit, fail: (Exception) -> Unit) {
        //region previous version
//        val localAccount = accountRepository.getAccount(account.fireBaseId)
//        if (localAccount != null) {
//            accountRepository.saveToSharedPrefs(localAccount)
//            firestoreRepository.create(account, {
//                success(localAccount)
//            }, {
//
//            })
//
//        } else {
//            firestoreRepository.get(account.fireBaseId, { remoteAccount ->
//                withContext(Dispatchers.Default) {
//                    accountRepository.saveToDatabase(remoteAccount)
//                    success(remoteAccount)
//                }
//            }, {
//
//            })
//        }
        //endregion

        val localAccount = DataProvider.getAccountById(account.fireBaseId)
        if (localAccount != null) {
            DataProvider.saveCurrentAccount(localAccount)
            DataProvider.createAccountDocument(account,
                    {
                        success(localAccount)
                    },
                    {
                        fail(FirestoreExceptionCreatingAccountDoc(it.message
                                ?: it.localizedMessage))
                    }
            )

        } else {
            val remoteAccount = DataProvider.getAccountDocument(account.fireBaseId)
            if (remoteAccount != null) {
                DataProvider.saveAccount(remoteAccount)
                DataProvider.saveCurrentAccount(remoteAccount)
                success(remoteAccount)
            } else {
                fail(FirestoreExceptionGettingAccountDoc("Something went wrong"))
            }
        }
    }


    private fun validPassword(password: String): Boolean {
        val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$")
        return pattern.matcher(password).matches()
    }

    private fun checkEmailError(email: String, error: (String) -> Unit) {
        if (email.length < 8) error("Too short Email")
        else error("Invalid Email")
    }

    private fun checkPasswordEmail(password: String, error: (String) -> Unit) {
        if (password.length < 8) error("Too short Email")
        else error("Invalid Password")
    }
}