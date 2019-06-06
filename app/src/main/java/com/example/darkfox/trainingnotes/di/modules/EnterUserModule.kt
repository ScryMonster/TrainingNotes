package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity.EnterUserPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object EnterUserModule {
    private val scopeName = KoinScopes.EnterUser.scopeName


    val module = module {
        scope<EnterUserContract.Presenter>(scopeName){
            EnterUserPresenter()
        }
    }
}