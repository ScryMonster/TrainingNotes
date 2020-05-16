package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.root.IRootInteractor
import com.example.darkfox.trainingnotes.arch.domain.root.RootInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.RootContract
import com.example.darkfox.trainingnotes.arch.ui.root.RootPresenter
import org.koin.dsl.module

object RootActModule {


    val module = module {
            single<IRootInteractor> { RootInteractor() }
            single<RootContract.Presenter> { RootPresenter(interactor = get()) }
    }

}