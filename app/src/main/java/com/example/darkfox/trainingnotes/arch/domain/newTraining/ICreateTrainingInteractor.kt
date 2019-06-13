package com.example.darkfox.trainingnotes.arch.domain.newTraining

import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.dto.gym.Training
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay

interface ICreateTrainingInteractor {
    fun getCurrentUser(): Account?
    //    suspend fun saveTraining(day: TrainingDay)
//    suspend fun updateTraining(day: TrainingDay)
    suspend fun saveTrraining(dayHolder: TrainingDayHolder, accountId: String,success:()->Unit,fail:(Exception)->Unit)
}