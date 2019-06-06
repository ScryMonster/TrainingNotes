package com.example.darkfox.trainingnotes.arch.domain.trainings

import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay

interface ITrainingDayInteractor {
    suspend fun getTrainingDaysById(id: String): List<TrainingDay>
    fun getCurrentUser(): Account?
}