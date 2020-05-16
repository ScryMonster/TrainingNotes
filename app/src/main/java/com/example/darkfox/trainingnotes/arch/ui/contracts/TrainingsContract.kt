package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingDay

interface TrainingsContract {

    interface View:IProgressView{
        fun setTrainingDays(days:List<TrainingDay>)
        fun returnToEnterUserFlow()
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun getTrainingDaysById()
        fun deleteTraining(day: TrainingDay)
        fun removeTraining(day: TrainingDay)
    }
}