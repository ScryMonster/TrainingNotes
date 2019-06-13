package com.example.darkfox.trainingnotes.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.example.darkfox.trainingnotes.arch.repository.local.AccountRepository
import com.example.darkfox.trainingnotes.arch.repository.local.PermissionsLocalRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.FirebaseAuthRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.IRemoteRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.TrainingDaysRepository
import com.example.darkfox.trainingnotes.arch.repository.remote.UserFirestoreRepository
import com.example.darkfox.trainingnotes.database.TrainingsDatabase
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.extensions.*
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.AccountManager
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.PermissionsManager
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.TokenManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.koin.core.Koin
import org.koin.core.KoinContext
import org.koin.dsl.module.module

object BaseModule {
    val AccountRepositoryName = "AccountRepositoryName"


    val module = module {
        single { provideSharedPrefs(get()) }

        single<AccountManager> {
            provideAccountManager(get(), get())
        }
        single {
            TrainingsDatabase.getInstance(get())!!
        }

        single {
            TrainingsDatabase.getInstance(get())?.getAccountsDao()!!
        }

        single {
            TrainingsDatabase.getInstance(get())?.getTrainingDaysDao()!!
        }

        single {
            AccountRepository(get(), get())
        }
        single {
            TrainingDaysRepository(get())
        }
        single{
            PermissionsLocalRepository(get())
        }
        single<TokenManager> {
            provideTokenManager(get(), get())
        }
        single<PermissionsManager> {
            providePermissionsManager(get(), get())
        }

        single {
            FirebaseAuthRepository(FirebaseAuth.getInstance())
        }

        single {
            FirebaseFirestore.getInstance()
        }

        single<IRemoteRepository<Account>> {
            UserFirestoreRepository(get())
        }


    }

    private fun provideSharedPrefs(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    private fun provideAccountManager(sharedPreferences: SharedPreferences, gson: Gson) = object : AccountManager {

        override val isAccountExist: Boolean
            get() = sharedPreferences.containsAccount()

        override fun setAccountUserName(firstName: String, lastName: String) {
            sharedPreferences.getAccount(gson)
                    ?.let {
                        it.copy(firstName = firstName, lastName = lastName)
                    }
                    ?.transformToJson(gson)
                    ?.also { json ->
                        sharedPreferences.storeAccount(json)
                    }
        }

        override fun setAccountEmail(email: String) {
            sharedPreferences.getAccount(gson)
                    ?.let {
                        it.copy(email = email)
                    }
                    ?.transformToJson(gson)
                    ?.also { json ->
                        sharedPreferences.storeAccount(json)
                    }
        }

        override fun storeAccount(account: Account) {
            sharedPreferences.storeAccount(account.transformToJson(gson))
        }

        override fun restoreAccount(): Account? = sharedPreferences.getAccount(gson)


        override fun removeAccount() {
            sharedPreferences.removeAccount()
        }

    }

    private fun provideTokenManager(sharedPreferences: SharedPreferences, gson: Gson) = object : TokenManager {
        override val isTokenExist: Boolean
            get() = sharedPreferences.contains(ACCESS_TOKEN_PREF)

        override fun storeToken(accessToken: String, refreshToken: String) {
            sharedPreferences.edit {
                putString(ACCESS_TOKEN_PREF, accessToken)
                putString(REFRESH_TOKEN_PREF, refreshToken)
            }
        }

        override fun restoreAccessToken(): String? = sharedPreferences.getString(ACCESS_TOKEN_PREF, "")

        override fun restoreRefreshToken(): String = sharedPreferences.getString(REFRESH_TOKEN_PREF, "")

    }

    private fun providePermissionsManager(sharedPreferences: SharedPreferences, gson: Gson) = object : PermissionsManager {
        override val arePermissionsExist: Boolean
            get() = sharedPreferences.contains(PERMISSIONS_KEY)


        override fun storePermissions(permissions: ReadWriteStoragePermission) {
            sharedPreferences.edit {
                putString(PERMISSIONS_KEY, gson.toJson(permissions))
            }
        }

        override fun restorePermissions(): ReadWriteStoragePermission {
            val permissionsInString = sharedPreferences.getString(PERMISSIONS_KEY, null)
            return if (permissionsInString != null) gson.fromJson<ReadWriteStoragePermission>(permissionsInString, ReadWriteStoragePermission::class.java)
            else ReadWriteStoragePermission()
        }

    }

    private val ACCESS_TOKEN_PREF = "com.example.darkfox.trainingnotes::ACCESS_TOKEN_PREF"
    private val REFRESH_TOKEN_PREF = "com.example.darkfox.trainingnotes::REFRESH_TOKEN_PREF"
    private val PERMISSIONS_KEY = "com.example.darkfox.trainingnotes::PERMISSIONS_KEY"
}