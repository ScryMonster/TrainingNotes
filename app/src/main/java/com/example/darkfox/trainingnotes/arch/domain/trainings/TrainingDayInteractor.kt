package com.example.darkfox.trainingnotes.arch.domain.trainings

import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay

class TrainingDayInteractor : ITrainingDayInteractor {

    override suspend fun getTrainingDaysById(id: String): List<TrainingDay> = DataProvider.getTrainingDaysById(id)
            ?: arrayListOf()


    override fun getCurrentUser() = DataProvider.getCurrentAccount()

}