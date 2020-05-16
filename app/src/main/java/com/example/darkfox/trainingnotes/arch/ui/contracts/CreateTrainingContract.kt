package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView
import com.example.darkfox.trainingnotes.models.dto.TrainingDayHolder

interface CreateTrainingContract {

    interface View : IProgressView{
        fun returnToEnterUserFlow()
        fun goBack()
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun saveTraining(dayHolder: TrainingDayHolder)
//        fun updateTraining(trainings: Training)
    }
}