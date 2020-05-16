package com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs

import com.example.darkfox.trainingnotes.models.dto.Account

interface IAccountManager {

    val isAccountExist: Boolean

    fun setAccountUserName(firstName: String, lastName: String)

    fun setAccountEmail(email: String)

    fun storeAccount(account: Account)

    fun restoreAccount(): Account?

    fun removeAccount()
}