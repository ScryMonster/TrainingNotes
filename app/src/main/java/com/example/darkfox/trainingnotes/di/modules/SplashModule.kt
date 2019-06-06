package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.arch.domain.splash.SplashInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.SplashContract
import com.example.darkfox.trainingnotes.arch.ui.splash.SplashPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import com.example.darkfox.trainingnotes.utils.permission.PermissionHelper
import org.koin.dsl.module.module

object SplashModule {
    private val scopeName = KoinScopes.SPLASH.scopeName

    val module = module {
        scope(scopeName) { PermissionHelper() }



        scope<ISplashInteractor>(scopeName) {
            SplashInteractor(get())
        }

        scope<SplashContract.Presenter>(scopeName) {
            SplashPresenter(get())
        }
    }
}