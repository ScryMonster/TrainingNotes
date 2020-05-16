package com.example.darkfox.trainingnotes.arch.ui.wizzards.names.props

import com.example.darkfox.trainingnotes.arch.base.ui.BasePresenter
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.models.dto.Account

class WizzardNamesPresenter : BasePresenter<WizzardContract.View>(),WizzardContract.Presenter{
    override fun saveAccount(account: Account?) {}

}