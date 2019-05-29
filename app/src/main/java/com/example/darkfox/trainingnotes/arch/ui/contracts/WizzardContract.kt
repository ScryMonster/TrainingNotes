package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.dto.Account

interface WizzardContract {

    interface View : BaseContract.View{

    }

    interface Presenter : BaseContract.Presenter<WizzardContract.View>{
        fun saveAccount(account:Account?,success:(Account)->Unit)
    }
}