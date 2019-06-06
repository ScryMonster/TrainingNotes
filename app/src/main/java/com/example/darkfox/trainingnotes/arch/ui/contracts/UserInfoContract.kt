package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.di.IKoinView
import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView
import com.example.darkfox.trainingnotes.dto.Account

interface UserInfoContract {

    interface View: IProgressView{
        fun fillViewWithUser(account: Account)
        fun returnToEnterUserFlow()
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun getCurrentUser()
    }
}