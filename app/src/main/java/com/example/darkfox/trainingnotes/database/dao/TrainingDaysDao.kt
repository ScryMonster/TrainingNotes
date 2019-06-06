package com.example.darkfox.trainingnotes.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay

@Dao
interface TrainingDaysDao : BaseDao<TrainingDay> {

    @Query("SELECT * FROM trainingDays WHERE accountId =:id")
    fun getAllByAccountId(id:String) : List<TrainingDay>?
}