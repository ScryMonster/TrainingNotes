package com.example.darkfox.trainingnotes.arch.repository.local

import com.example.darkfox.trainingnotes.database.dao.AccountDao
import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.IAccountManager

class AccountRepository(private val accountManager: IAccountManager,
                        private val accountDao: AccountDao) {


    fun saveToSharedPrefs(item: Account) {
        accountManager.storeAccount(item)
    }

    fun getFromSharedPrefs() : Account?  = accountManager.restoreAccount()


    fun saveToDatabase(account: Account) {
            accountDao.insert(account)
    }

    fun getAccount(id: String) = accountDao.getAccount(id)

    fun updateAccount(account: Account) = accountDao.update(account)

}