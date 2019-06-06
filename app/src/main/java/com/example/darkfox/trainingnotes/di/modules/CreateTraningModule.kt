package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.ui.contracts.CreateTrainingContract
import com.example.darkfox.trainingnotes.arch.ui.createTraining.CreateTrainingPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object CreateTraningModule {
    private val scopeName = KoinScopes.CREATE_TRAINING.scopeName

    val module = module {
        scope<CreateTrainingContract.Presenter>(scopeName){
            CreateTrainingPresenter()
        }
    }
}