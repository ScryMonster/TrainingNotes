package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.trainings.ITrainingDayInteractor
import com.example.darkfox.trainingnotes.arch.domain.trainings.TrainingDayInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.TrainingsContract
import com.example.darkfox.trainingnotes.arch.ui.trainings.TrainingsPresenter
import org.koin.dsl.module

object TrainingsModule {
    val module = module {
        single<ITrainingDayInteractor> {
            TrainingDayInteractor()
        }

        single<TrainingsContract.Presenter> {
            TrainingsPresenter(get())
        }
    }
}