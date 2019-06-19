package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView

interface RegisterContract {

    interface View : IProgressView{

    }

    interface Presenter : BaseContract.Presenter<RegisterContract.View>{
        fun checkEmail(email:String)
        fun checkPassword(password:String)
    }
}