package com.example.darkfox.trainingnotes.application

import android.app.Application
import com.example.darkfox.trainingnotes.BuildConfig
import com.example.darkfox.trainingnotes.di.modules.*
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger

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
//                        SignInModule.module,
                        EnterUserActivityModule.module,
                        WizzardModule.module,
                        UserInfoModule.module,
                        TrainingsModule.module,
                        CreateTraningModule.module,
                        AddWarmUpModule.module,
                        SearchModule.module,
                        SearchSettingsModule.module,
                        EnterUserFragmentsModule.module),
                logger = AndroidLogger(BuildConfig.DEBUG)
        )
    }


}