package com.example.darkfox.trainingnotes.application

import android.app.Application
import com.example.darkfox.trainingnotes.BuildConfig
import com.example.darkfox.trainingnotes.di.modules.*
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger
import org.koin.dsl.module.module

class TrainingNotes : Application() {
    private val module = org.koin.dsl.module.module {
        single { this@TrainingNotes.applicationContext }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this,
                listOf(NetModule.module,
                        BaseModule.module,
                        SplashModule.module,
                        RootActModule.module,
                        SignInModule.module,
                        EnterUserModule.module,
                        WizzardModule.module),
                logger = AndroidLogger(BuildConfig.DEBUG)
        )
    }


}