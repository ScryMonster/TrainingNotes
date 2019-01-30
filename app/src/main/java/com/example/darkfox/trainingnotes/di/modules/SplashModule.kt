package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object SplashModule {

    val module = module {
        viewModel{ SplashViewModel }
    }
}