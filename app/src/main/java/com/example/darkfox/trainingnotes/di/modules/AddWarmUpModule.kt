package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.AddWarmUpContract
import com.example.darkfox.trainingnotes.arch.ui.dialogs.warmUp.AddWarmUpPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object AddWarmUpModule {
    private val scopeName = KoinScopes.ADD_WARM_UP.scopeName


    val module = module {
        scope<AddWarmUpContract.Presenter>(scopeName){
            AddWarmUpPresenter()
        }
    }
}