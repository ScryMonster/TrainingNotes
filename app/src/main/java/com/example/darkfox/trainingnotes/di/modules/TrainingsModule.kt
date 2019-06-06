package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.trainings.ITrainingDayInteractor
import com.example.darkfox.trainingnotes.arch.domain.trainings.TrainingDayInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.TrainingsContract
import com.example.darkfox.trainingnotes.arch.ui.trainings.TrainingsPresenter
import com.example.darkfox.trainingnotes.utils.enums.KoinScopes
import org.koin.dsl.module.module

object TrainingsModule {

    private val scopeName = KoinScopes.TRAININGS.scopeName

    val module = module {
        scope<ITrainingDayInteractor>(scopeName){
            TrainingDayInteractor()
        }

        scope<TrainingsContract.Presenter>(scopeName){
            TrainingsPresenter(get())
        }
    }
}