package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.enterUser.EnterUserInteractor
import com.example.darkfox.trainingnotes.arch.domain.enterUser.IEnterUserInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserFragmentContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.login.EnterUserFragmentPresenter
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.register.RegisterPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object EnterUserFragmentsModule {
    private val scopeName= KoinScopes.ENTER_USER_FRAGMENTS.scopeName

    val module = org.koin.dsl.module.module {
        scope<IEnterUserInteractor>(scopeName){
            EnterUserInteractor()
        }

        scope<EnterUserFragmentContract.Presenter>(scopeName,name = SignIn){
            EnterUserFragmentPresenter(get())
        }

        scope<EnterUserFragmentContract.Presenter>(scopeName,name = Register){
            RegisterPresenter(get())
        }
    }


    val SignIn = "SignIn"
    val Register = "register"
}