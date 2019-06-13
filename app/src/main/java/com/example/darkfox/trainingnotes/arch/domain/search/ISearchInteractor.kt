package com.example.darkfox.trainingnotes.arch.domain.search

import com.example.darkfox.trainingnotes.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.dto.gym.Training

interface ISearchInteractor {
    suspend fun searchByName(name:String) : List<TrainingDayHolder>

    fun clearLastSearchedList()
}