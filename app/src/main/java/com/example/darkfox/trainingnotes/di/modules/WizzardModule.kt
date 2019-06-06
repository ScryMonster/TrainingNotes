package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.wizzard.IWizzardInteractor
import com.example.darkfox.trainingnotes.arch.domain.wizzard.WizzardInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.arch.ui.wizzards.names.WizzardPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object WizzardModule {

    private val scopeName = KoinScopes.WIZZARD.scopeName

    val module = module{

        scope<IWizzardInteractor>(scopeName){
            WizzardInteractor()
        }

        scope<WizzardContract.Presenter>(scopeName){
            WizzardPresenter(get())
        }
    }
}