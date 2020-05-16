package com.example.darkfox.trainingnotes.arch.domain.newTraining

import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.models.dto.TrainingDayHolder

interface ICreateTrainingInteractor {
    fun getCurrentUser(): Account?
    //    suspend fun saveTraining(day: TrainingDay)
//    suspend fun updateTraining(day: TrainingDay)
    suspend fun saveTrraining(dayHolder: TrainingDayHolder, accountId: String,success:()->Unit,fail:(Exception)->Unit)
}