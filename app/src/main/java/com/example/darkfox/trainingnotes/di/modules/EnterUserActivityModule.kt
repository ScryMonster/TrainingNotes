package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.activity.EnterUserPresenter
import org.koin.dsl.module


object EnterUserActivityModule {


    val module = module {
            single<EnterUserContract.Presenter>{ EnterUserPresenter() }
    }
}