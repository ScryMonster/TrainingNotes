package com.example.darkfox.trainingnotes.di.modules

import com.example.darkfox.trainingnotes.arch.domain.newTraining.CreateTrainingInteractor
import com.example.darkfox.trainingnotes.arch.domain.newTraining.ICreateTrainingInteractor
import com.example.darkfox.trainingnotes.arch.ui.contracts.CreateTrainingContract
import com.example.darkfox.trainingnotes.arch.ui.createTraining.CreateTrainingPresenter
import org.koin.dsl.module

object CreateTraningModule {
    val module = module {
        single<ICreateTrainingInteractor> {
            CreateTrainingInteractor()
        }
        single<CreateTrainingContract.Presenter> {
            CreateTrainingPresenter(interactor = get())
        }

    }
}