package com.example.darkfox.trainingnotes.di.modules

import android.content.SharedPreferences
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.extensions.*
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.AccountManager
import com.google.gson.Gson
import org.koin.dsl.module.module

object BaseModule {

    val module = module {
        single<AccountManager> {
            provideAccountManager(get(),get())
        }
    }


    private fun provideAccountManager(sharedPreferences: SharedPreferences, gson: Gson) = object : AccountManager {
        override val isTokenExist: Boolean
            get() = sharedPreferences.contains(ACCESS_TOKEN_PREF)

        override val isAccountExist: Boolean
            get() = sharedPreferences.containsAccount()


        override fun storeToken(accessToken: String, refreshToken: String) {
            sharedPreferences.edit().putString(ACCESS_TOKEN_PREF, accessToken)
        }

        override fun restoreAccessToken(): String?  = sharedPreferences.getString(ACCESS_TOKEN_PREF, "")


        override fun restoreRefreshToken(): String = sharedPreferences.getString(REFRESH_TOKEN_PREF, "")


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
                    ?.also {json->
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

    private val ACCESS_TOKEN_PREF = "com.example.darkfox.trainingnotes::ACCESS_TOKEN_PREF"
    private val REFRESH_TOKEN_PREF = "com.example.darkfox.trainingnotes::REFRESH_TOKEN_PREF"
}