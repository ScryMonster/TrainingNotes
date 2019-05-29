package com.example.darkfox.trainingnotes.arch.domain.enterUser

import android.util.Log
import android.util.Patterns
import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.BaseFirestoreRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.FirebaseAuthRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.IRemoteRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.UserFirestoreRepository
import com.example.darkfox.trainingnotes.dto.Account
import java.util.regex.Pattern

class EnterUserInteractor(private val accountRepository: LocalRepository<Account>,
                          private val firebaseAuthRepository: FirebaseAuthRepository,
                          private val firestoreUserFirestoreRepository: IRemoteRepository<Account>) : IEnterUserInteractor {

    override fun checkEmail(email: String, success: () -> Unit, error: (String) -> Unit) {
        if (email.length > 8 && Patterns.EMAIL_ADDRESS.matcher(email).matches()) success()
        else checkEmailError(email, error)
    }

    override fun checkPassword(password: String, success: () -> Unit, error: (String) -> Unit) {
        if (password.length > 8 && validPassword(password)) success()
        else checkPasswordEmail(password, error)
    }

    override fun signIn(email: String?, password: String?, success: (Account) -> Unit, error: (Exception) -> Unit) {
        if (email != null && password != null) {
            firebaseAuthRepository.signIn(email, password,
                    {
                        //                        Account()
                    },
                    {
                        Log.i("Firebase Auth error: ", it.toString())
                        error.invoke(it)
                    })
        }
    }

    override fun register(email: String?, password: String?, success: (Account) -> Unit, error: (java.lang.Exception) -> Unit) {
        if (email != null && password != null) {
            firebaseAuthRepository.register(email, password,
                    { firebaseUser ->
                        Account(fireBaseId = firebaseUser.uid,
                                email = firebaseUser.email)
                                .also {
                                    accountRepository.save(it)
//                                    success(it)
                                    firestoreUserFirestoreRepository.create(it,
                                            {
                                                Log.d("FireStore","SAVED")
                                            },
                                            {
                                                Log.d("FireStore","NOT SAVED. Error ${it.localizedMessage}")
                                            })
                                }
                    },
                    {
                        Log.i("Firebase Auth error: ", it.toString())
                        error.invoke(it)
                    })
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