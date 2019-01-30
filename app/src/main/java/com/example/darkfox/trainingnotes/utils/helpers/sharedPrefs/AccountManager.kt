package com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs

import com.example.darkfox.trainingnotes.dto.Account

interface AccountManager {

    val isTokenExist: Boolean

    val isAccountExist: Boolean

    fun storeToken(accessToken: String, refreshToken: String)

    fun restoreAccessToken(): String?

    fun restoreRefreshToken(): String

    fun setAccountUserName(firstName: String, lastName: String)

    fun setAccountEmail(email: String)

    fun setAccountPhoneNumber(phoneNumber: String)

    fun storeAccount(account: Account)

    fun restoreAccount(): Account?

    fun removeAccount()
}