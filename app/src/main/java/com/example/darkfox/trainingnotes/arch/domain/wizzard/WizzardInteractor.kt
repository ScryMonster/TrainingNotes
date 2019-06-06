package com.example.darkfox.trainingnotes.arch.domain.wizzard

import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.dto.Account

class WizzardInteractor() : IWizzardInteractor {
    override suspend fun saveAccount(account: Account, success: () -> Unit,fail:(Exception)->Unit) {
        DataProvider.getCurrentAccount()
                .also { currentAccount ->
                    if (currentAccount != null){
                        currentAccount.firstName = account.firstName
                        currentAccount.lastName = account.lastName
                        currentAccount.properties = account.properties
                        DataProvider.saveCurrentAccount(currentAccount)
                        DataProvider.updateAccount(currentAccount)
                        DataProvider.createAccountDocument(currentAccount,success,fail)
                    }
                    else{
                        fail(Exception("Error While getting current account"))
                    }
                }
    }
}