package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.models.dto.Account

interface WizzardContract {

    interface View : BaseContract.View{
        fun finishFlow()
    }

    interface Presenter : BaseContract.Presenter<WizzardContract.View>{
        fun saveAccount(account:Account?)
    }
}