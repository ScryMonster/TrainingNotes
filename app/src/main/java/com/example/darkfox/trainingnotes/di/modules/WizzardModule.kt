package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.wizzard.IWizzardInteractor
import com.example.darkfox.trainingnotes.arch.domain.wizzard.WizzardInteractor
import com.example.darkfox.trainingnotes.arch.repository.local.AccountRepository
import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.arch.ui.contracts.WizzardContract
import com.example.darkfox.trainingnotes.arch.ui.wizzards.names.WizzardPresenter
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module
import org.koin.experimental.builder.scope

object WizzardModule {

    private val scopeName = KoinScopes.WIZZARD.scopeName

    val module = module{
        scope<LocalRepository<Account>>(scopeName) {
            AccountRepository(get())
        }

        scope<IWizzardInteractor>(scopeName){
            WizzardInteractor(get(name = BaseModule.AccountRepositoryName))
        }

        scope<WizzardContract.Presenter>(scopeName){
            WizzardPresenter(get())
        }
    }
}