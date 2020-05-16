package com.example.darkfox.trainingnotes.arch.ui.contracts

import android.app.Activity
import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView
import com.example.darkfox.trainingnotes.models.dto.Account

interface SplashContract {

    interface View : IProgressView{
        fun openRootActivity(account:Account)
        fun openLogInActivity()

    }

    interface Presenter : BaseContract.Presenter<SplashContract.View>{
        fun attemptRequestPermissions(activity: Activity)
        fun onRequestPermissionsResult(requestCode: Int,grantResults: IntArray,permissions:Array<String>)
    }
}