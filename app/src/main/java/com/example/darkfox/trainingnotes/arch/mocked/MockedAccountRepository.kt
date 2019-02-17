package com.example.darkfox.trainingnotes.arch.mocked

import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.AccountManager

class MockedAccountRepository(private val accountManager: AccountManager) : LocalRepository<Account> {
    override fun save(item: Account, success: () -> Unit, error: (Exception) -> Unit) {

    }

    override fun restore(success: (Account) -> Unit, error: (Exception) -> Unit) {
        val acc = accountManager.restoreAccount()
        if (acc != null) success(acc) else success(mockAccount())
    }

    private fun mockAccount() = Account(1,
            "nikitots@i.ua",
            "Nikita",
            "Totskiy",
            "ffff")
}