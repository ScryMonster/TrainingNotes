package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.AddWarmUpContract
import com.example.darkfox.trainingnotes.arch.ui.dialogs.warmUp.AddWarmUpPresenter
import org.koin.dsl.module


object AddWarmUpModule {

    val module = module {
            single<AddWarmUpContract.Presenter>{
                AddWarmUpPresenter()
        }
    }
}