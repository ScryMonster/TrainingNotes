package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.wizzard.IWizzardInteractor
import com.example.darkfox.trainingnotes.arch.domain.wizzard.WizzardInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.arch.ui.wizzards.names.props.WizzardNamesPresenter
import com.example.darkfox.trainingnotes.arch.ui.wizzards.names.props.WizzardPropsPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

object WizzardModule {
    val module = module{
        single<IWizzardInteractor> {
            WizzardInteractor()
        }

        single<WizzardContract.Presenter>(named(WIZZARD_PROPS)) {
            WizzardPropsPresenter(get())
        }

        single<WizzardContract.Presenter>(named(WIZZARD_NAME)) {
            WizzardNamesPresenter()
        }
    }

    val WIZZARD_NAME = "wizzardNames"
    val WIZZARD_PROPS = "wizzardProps"
}