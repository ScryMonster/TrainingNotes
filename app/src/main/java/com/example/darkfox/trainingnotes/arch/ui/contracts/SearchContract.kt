package com.example.darkfox.trainingnotes.arch.ui.contracts

import com.example.darkfox.trainingnotes.arch.base.ui.BaseContract
import com.example.darkfox.trainingnotes.arch.base.ui.IProgressView
import com.example.darkfox.trainingnotes.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.dto.gym.Training
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import com.example.darkfox.trainingnotes.utils.enums.SearchType

interface SearchContract {

    interface View : IProgressView{
        fun setTrainings(trainings:List<TrainingDayHolder>)
        fun setClearBtnVisibility(visible:Boolean)
        fun warningMeaage(type:SearchType)
    }

    interface Presenter:BaseContract.Presenter<View>{
        fun searchByName(name:String)
        fun searchByGroup(groups:List<MuscleGroups>?)
        fun searchByDate(midnight:Long?)
        fun clearLastSearchedList()
    }
}