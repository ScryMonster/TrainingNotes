package com.example.darkfox.trainingnotes.arch.domain.newTraining

import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.models.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.utils.extensions.todaysMidnight

class CreateTrainingInteractor : ICreateTrainingInteractor {
    override fun getCurrentUser(): Account? = DataProvider.getCurrentAccount()

    private suspend fun saveTraining(day: TrainingDay,success: () -> Unit, fail: (Exception) -> Unit) {
        DataProvider.saveTrainingDay(day)
        DataProvider.saveDayToServer(day,success, fail)
    }

    private suspend fun updateTraining(day: TrainingDay,success: () -> Unit, fail: (Exception) -> Unit) {
        DataProvider.updateTraining(day)
        DataProvider.updateTrainingDayServer(day,success, fail)
    }

    override suspend fun saveTrraining(dayHolder: TrainingDayHolder, accountId: String, success: () -> Unit, fail: (Exception) -> Unit) {
        DataProvider.getTodaysTrainingDay(dayHolder.dayId ?: todaysMidnight())?.let { day ->
            (day.trainings as ArrayList).also { trainings ->
                if (trainings.any { it.id == dayHolder.training.id }) {
                    val oldTraining = trainings.find { it.id == dayHolder.training.id }
                    val indexOf = trainings.indexOf(oldTraining)
                    trainings.removeAt(indexOf)
                    trainings.add(indexOf,dayHolder.training)
                } else trainings.add(dayHolder.training)
            }
            updateTraining(day,success, fail)
        } ?: saveTraining(TrainingDay(todaysMidnight(), accountId, listOf(dayHolder.training)),success, fail)
    }



}