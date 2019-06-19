package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.di.IKoinView
import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.navigation.ISignInNavigation

interface EnterUserContract {

    interface View: BaseContract.View,IKoinView{
        fun showProgress()
        fun hideProgress()
        fun goToRootActivity()
        fun closeWizzardScreens()
    }

    interface Presenter: BaseContract.Presenter<EnterUserContract.View>{
        fun saveEmptyProperties()
    }
}