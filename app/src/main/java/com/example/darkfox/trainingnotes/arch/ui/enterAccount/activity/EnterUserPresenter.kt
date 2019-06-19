package com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserContract
import com.example.darkfox.trainingnotes.dto.UserProperties
import kotlinx.coroutines.launch

class EnterUserPresenter : BasePresenter<EnterUserContract.View>(),EnterUserContract.Presenter {
    override fun saveEmptyProperties() {
        uiScope.launch {
            DataProvider.getCurrentAccount()?.let { account ->
                val props = UserProperties(0,0.0)
                account.firstName = ""
                account.lastName = ""
                account.properties = props
                DataProvider.saveCurrentAccount(account)
                DataProvider.updateAccount(account)
                DataProvider.createAccountDocument(account,{view?.goToRootActivity()},{})

            }
        }
    }
}