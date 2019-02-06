package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.arch.domain.splash.SplashInteractor
import com.example.darkfox.trainingnotes.arch.repository.local.AccountRepository
import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.arch.ui.splash.viewmodel.SplashViewModel
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.scope

object SplashModule {

    val module = module {
        scope<LocalRepository<Account>> (KoinScopes.SPLASH.scopeName){ AccountRepository(get()) }
        scope<ISplashInteractor>(KoinScopes.SPLASH.scopeName){SplashInteractor(get())  }
        viewModel{ SplashViewModel(get()) }
    }
}