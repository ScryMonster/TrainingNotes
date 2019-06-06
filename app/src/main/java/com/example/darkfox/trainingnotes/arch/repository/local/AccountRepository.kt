package com.example.darkfox.trainingnotes.arch.repository.local

import com.example.darkfox.trainingnotes.database.dao.AccountDao
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.errors.UserNotExist
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.AccountManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepository(private val accountManager: AccountManager,
                        private val accountDao: AccountDao) {


    fun saveToSharedPrefs(item: Account) {
        accountManager.storeAccount(item)
    }

    fun getFromSharedPrefs() : Account?  = accountManager.restoreAccount()


    fun saveToDatabase(account: Account) {
//        withContext(Dispatchers.Default) {
            accountDao.insert(account)
//        }
    }

    fun getAccount(id: String) = accountDao.getAccount(id)

    fun updateAccount(account: Account) = accountDao.update(account)

}