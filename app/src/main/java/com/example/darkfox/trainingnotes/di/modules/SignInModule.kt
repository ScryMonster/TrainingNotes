package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.enterUser.IEnterUserInteractor
import com.example.darkfox.trainingnotes.arch.domain.enterUser.EnterUserInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.EnterUserFragmentContract
import com.example.darkfox.trainingnotes.arch.ui.enterAccount.login.EnterUserFragmentPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object SignInModule {
    private val scopeName = KoinScopes.LOG_IN.scopeName
    private val AccountRepositoryName = "AccountRepositoryName"



    val module = module {
        scope<IEnterUserInteractor>(scopeName){
            EnterUserInteractor(get(name = AccountRepositoryName),get(),get())
        }

        scope<EnterUserFragmentContract.Presenter>(scopeName){
            EnterUserFragmentPresenter(get())
        }
    }

}