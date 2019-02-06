package com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs

import com.example.darkfox.trainingnotes.dto.Account

interface AccountManager {

    val isAccountExist: Boolean

    fun setAccountUserName(firstName: String, lastName: String)

    fun setAccountEmail(email: String)

    fun storeAccount(account: Account)

    fun restoreAccount(): Account?

    fun removeAccount()
}