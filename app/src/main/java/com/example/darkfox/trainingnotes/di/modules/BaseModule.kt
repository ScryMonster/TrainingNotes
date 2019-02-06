package com.example.darkfox.trainingnotes.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.extensions.*
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.AccountManager
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.TokenManager
import com.google.gson.Gson
import org.koin.core.Koin
import org.koin.core.KoinContext
import org.koin.dsl.module.module

object BaseModule {

    val module = module {
        single { provideSharedPrefs(get()) }
        single<AccountManager> {
            provideAccountManager(get(), get())
        }
        single<TokenManager> {
            provideTokenManager(get(), get())
        }
    }

    private fun provideSharedPrefs(context:Context) = PreferenceManager.getDefaultSharedPreferences(context)

    private fun provideAccountManager(sharedPreferences: SharedPreferences, gson: Gson) = object : AccountManager {

        override val isAccountExist: Boolean
            get() = sharedPreferences.containsAccount()

        override fun setAccountUserName(firstName: String, lastName: String) {
            sharedPreferences.getAccount(gson)
                    ?.editName(firstName)
                    ?.editLastName(lastName)
                    ?.transformToJson(gson)
                    ?.also { json ->
                        sharedPreferences.storeAccount(json)
                    }
        }

        override fun setAccountEmail(email: String) {
            sharedPreferences.getAccount(gson)
                    ?.editEmail(email)
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

    private val ACCESS_TOKEN_PREF = "com.example.darkfox.trainingnotes::ACCESS_TOKEN_PREF"
    private val REFRESH_TOKEN_PREF = "com.example.darkfox.trainingnotes::REFRESH_TOKEN_PREF"
}