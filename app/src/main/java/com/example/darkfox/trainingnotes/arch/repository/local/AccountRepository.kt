package com.example.darkfox.trainingnotes.arch.repository.local

import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.errors.UserNotExist
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.AccountManager

class AccountRepository(private val accountManager: AccountManager) : LocalRepository<Account> {
    override fun save(item: Account) {
        accountManager.storeAccount(item)
    }

    override fun restore(success: (Account) -> Unit, error: (Exception) -> Unit) {
        val acc = accountManager.restoreAccount()
        if (acc != null) success(acc) else error(UserNotExist())
    }

}