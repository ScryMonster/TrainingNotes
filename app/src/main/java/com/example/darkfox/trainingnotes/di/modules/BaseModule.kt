package com.example.darkfox.trainingnotes.di.modules

import android.content.SharedPreferences
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.AccountManager
import org.koin.dsl.module.module

object BaseModule {

    private fun provideAccountManager(sharedPreferences: SharedPreferences) = object : AccountManager {
        override val isTokenExist: Boolean
            get() = sharedPreferences.contains(ACCESS_TOKEN_PREF)

        override val isAccountExist: Boolean
            get() = sharedPreferences.contains(ACCOUNT_PREF)


        override fun storeToken(accessToken: String, refreshToken: String) {

        }

        override fun restoreAccessToken(): String? {

        }

        override fun restoreRefreshToken(): String {

        }

        override fun setAccountUserName(firstName: String, lastName: String) {

        }

        override fun setAccountEmail(email: String) {

        }

        override fun setAccountPhoneNumber(phoneNumber: String) {

        }

        override fun storeAccount(account: Account) {

        }

        override fun restoreAccount(): Account? {

        }

        override fun removeAccount() {

        }

    }

    val module = module {
        single<AccountManager> {
            provideAccountManager(get())
        }
    }


    private val ACCOUNT_PREF = "com.example.darkfox.trainingnotes::ACCOUNT"
    private val ACCESS_TOKEN_PREF = "com.example.darkfox.trainingnotes::ACCESS_TOKEN_PREF"
    private val REFRESH_TOKEN_PREF = "com.example.darkfox.trainingnotes::REFRESH_TOKEN_PREF"
}