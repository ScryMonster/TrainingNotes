package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.di.IKoinView
import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.navigation.ISignInNavigation

interface EnterUserContract {

    interface View: BaseContract.View, ISignInNavigation,IKoinView{

    }

    interface Presenter: BaseContract.Presenter<EnterUserContract.View>{

    }
}