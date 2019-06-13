package com.example.darkfox.trainingnotes.arch.domain.search

import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.dto.gym.Training

class SearchInteractor : ISearchInteractor {

    private var allTrainings: List<TrainingDayHolder>? = null


    override suspend fun searchByName(name: String): List<TrainingDayHolder> {
        return if (allTrainings == null) {
            getTrainings().filter { it.training.name.contains(name) }
        } else allTrainings?.filter { it.training.name.contains(name) } ?: emptyList()
    }

    override fun clearLastSearchedList() {
        allTrainings = null
    }

    private suspend fun getTrainings() = DataProvider.getCurrentAccount()?.let { account ->
//        allTrainings = DataProvider.getTrainingDaysById(account.fireBaseId)?.flatMap { it.trainings }
        allTrainings = arrayListOf()
        DataProvider.getTrainingDaysById(account.fireBaseId)?.forEach { day ->
            day.trainings.forEach { training ->
                (allTrainings as ArrayList).add(TrainingDayHolder(day.id,training))
            }
        }
        return@let allTrainings
    } ?: emptyList()


}