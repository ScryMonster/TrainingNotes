package com.example.darkfox.trainingnotes.arch.domain.trainings

import com.example.darkfox.trainingnotes.arch.repository.DataProvider
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingDay

class TrainingDayInteractor : ITrainingDayInteractor {

    override suspend fun getTrainingDaysById(id: String): List<TrainingDay> {
        val daysFromDB = DataProvider.getTrainingDaysById(id) ?: arrayListOf()
        val firebaseId = DataProvider.getCurrentAccount()?.fireBaseId
        return if (daysFromDB.isEmpty()){
            val daysFromServerOfTheUser = DataProvider.getDaysFromServer().filter { it != null }.filter { it?.accountId == firebaseId } as List<TrainingDay>
            if (daysFromServerOfTheUser.isNotEmpty()){
                DataProvider.saveTrainingDays(daysFromServerOfTheUser)
            }
            daysFromServerOfTheUser
        }
        else daysFromDB
    }

    override suspend fun deleteTraining(day: TrainingDay,success: () -> Unit) {
        DataProvider.deleteTrainingDayLocaly(day)
        DataProvider.deleteTrainingDayFromServer(day,success)
    }

    override suspend fun removeTraining(day: TrainingDay) {
        DataProvider.removeTrainingLocaly(day)
        DataProvider.updateTrainingDayServer(day,{},{})
    }


    override fun getCurrentUser() = DataProvider.getCurrentAccount()

}