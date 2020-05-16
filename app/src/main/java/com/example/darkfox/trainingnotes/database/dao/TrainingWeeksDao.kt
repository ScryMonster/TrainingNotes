package com.example.darkfox.trainingnotes.database.dao

import androidx.room.Query
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingWeek

interface TrainingWeeksDao : BaseDao<TrainingWeek> {

    @Query("SELECT * FROM weeks WHERE accountID=:accountId")
    fun getAllWeeksOfAccount(accountId:Long) : List<TrainingWeek>

    @Query("SELECT * FROM weeks WHERE accountID =:accountId AND id =:weekId")
    fun getWeekById(accountId:Long,weekId:Long):TrainingWeek
}