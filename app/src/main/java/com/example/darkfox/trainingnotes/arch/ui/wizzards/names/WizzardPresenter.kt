package com.example.darkfox.trainingnotes.arch.ui.wizzards.names

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.domain.wizzard.IWizzardInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.dto.Account

class WizzardPresenter(private val interactor: IWizzardInteractor) : BasePresenter<WizzardContract.View>(),WizzardContract.Presenter {
    override fun saveAccount(account: Account?, success: (Account) -> Unit) {
        account?.let {  interactor.saveAccount(it,success) }
    }
}