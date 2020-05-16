package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract

interface EnterUserContract {

    interface View: BaseContract.View{
        fun showProgress()
        fun hideProgress()
        fun goToRootActivity()
        fun closeWizzardScreens()
    }

    interface Presenter: BaseContract.Presenter<EnterUserContract.View>{
        fun saveEmptyProperties()
    }
}