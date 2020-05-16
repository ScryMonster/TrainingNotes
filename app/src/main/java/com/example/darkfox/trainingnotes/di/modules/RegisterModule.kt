package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserFragmentContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.register.RegisterPresenter
import org.koin.dsl.module


object RegisterModule {

    val module = module{
            single<EnterUserFragmentContract.Presenter> { RegisterPresenter(interactor = get()) }
    }
}