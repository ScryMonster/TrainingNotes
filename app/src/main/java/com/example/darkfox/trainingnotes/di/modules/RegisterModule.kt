package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserFragmentContract
import com.example.darkfox.trainingnotes.arch.ui.contracts.RegisterContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.register.RegisterPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object RegisterModule {
    private val scopeName = KoinScopes.REGISTER.scopeName

    val module = module{
        scope<EnterUserFragmentContract.Presenter>(scopeName){
            RegisterPresenter(get())
        }
    }
}