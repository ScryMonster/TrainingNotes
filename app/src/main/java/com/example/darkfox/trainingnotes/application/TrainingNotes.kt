package com.example.darkfox.trainingnotes.application

import android.app.Application
import com.example.darkfox.trainingnotes.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TrainingNotes : Application() {
    private val module = module {
        single { this@TrainingNotes.applicationContext }
    }

    override fun onCreate() {
        super.onCreate()
        val modules = listOf(NetModule.module,
                        BaseModule.module,
                        SplashModule.module,
                        RootActModule.module,
                        EnterUserActivityModule.module,
                        WizzardModule.module,
                        UserInfoModule.module,
                        TrainingsModule.module,
                        CreateTraningModule.module,
                        AddWarmUpModule.module,
                        SearchModule.module,
                        SearchSettingsModule.module,
                        EnterUserFragmentsModule.module)

        startKoin{
            androidContext(this@TrainingNotes)
            logger(AndroidLogger())
            modules(modules)
        }
    }


}