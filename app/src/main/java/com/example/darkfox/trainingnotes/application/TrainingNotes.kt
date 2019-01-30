package com.example.darkfox.trainingnotes.application

import android.app.Application
import com.example.darkfox.trainingnotes.di.modules.NetModule
import org.koin.android.ext.android.startKoin

class TrainingNotes : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(NetModule.module))
    }
}