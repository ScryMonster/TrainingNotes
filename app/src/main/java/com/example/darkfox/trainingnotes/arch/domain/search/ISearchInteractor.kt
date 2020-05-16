package com.example.darkfox.trainingnotes.arch.domain.search

import com.example.darkfox.trainingnotes.models.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups

interface ISearchInteractor {
    suspend fun searchByName(name:String) : List<TrainingDayHolder>

    fun clearLastSearchedList()

    suspend fun searchByGroup(groups: List<MuscleGroups>?) :  List<TrainingDayHolder>

    suspend fun searchByDate(midnight: Long?) : List<TrainingDayHolder>

}