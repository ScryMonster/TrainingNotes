package com.example.darkfox.trainingnotes.arch.domain.trainings

import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingDay

interface ITrainingDayInteractor {
    suspend fun getTrainingDaysById(id: String): List<TrainingDay>
    fun getCurrentUser(): Account?
    suspend fun deleteTraining(day: TrainingDay,success: () -> Unit)
    suspend fun removeTraining(day: TrainingDay)

}