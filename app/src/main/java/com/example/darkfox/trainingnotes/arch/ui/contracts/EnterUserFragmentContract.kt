package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.EnterUserFlow

interface EnterUserFragmentContract {
    interface View: IProgressView {
        fun setEmailState(state:Boolean,message:String?=null)
        fun setPasswordState(state:Boolean,message:String?=null)
        fun navigateToWizzardFlow(account: Account)
        fun navigateToMainFlow()
    }

    interface Presenter:BaseContract.Presenter<View>{
        fun checkEmail(email:String)
        fun checkPassword(password:String)
//        fun setFlow(flow:EnterUserFlow)
        fun doStaff(email: String?, password: String?)
    }
}