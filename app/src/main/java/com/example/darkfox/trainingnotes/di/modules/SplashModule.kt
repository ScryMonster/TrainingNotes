package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.arch.domain.splash.SplashInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.SplashContract
import com.example.darkfox.trainingnotes.arch.ui.splash.SplashPresenter
import com.example.darkfox.trainingnotes.utils.permission.PermissionHelper
import org.koin.dsl.module

object SplashModule {
    val module = module {
        single { PermissionHelper() }



        single<ISplashInteractor> {
            SplashInteractor(get())
        }

        single<SplashContract.Presenter> {
            SplashPresenter(get())
        }
    }
}