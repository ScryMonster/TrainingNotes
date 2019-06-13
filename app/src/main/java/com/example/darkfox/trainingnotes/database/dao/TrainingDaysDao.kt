package com.example.darkfox.trainingnotes.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.darkfox.trainingnotes.dto.gym.Training
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay

@Dao
interface TrainingDaysDao : BaseDao<TrainingDay> {

    @Query("SELECT * FROM trainingDays WHERE accountId =:id")
    fun getAllByAccountId(id:String) : List<TrainingDay>?


    @Query("DELETE FROM trainingDays WHERE id =:id")
    fun deleteDayById(id:Long)

    @Query("SELECT * FROM trainingDays WHERE id =:id")
    fun getTodaysTrainingDay(id:Long) : TrainingDay?

//    @Query("SELECT trainings FROM trainingDays WHERE trainings CONTAINS ")
//    fun getTrainingsByName(name:String):List<Training>


    @Transaction
    fun updateTrainingById(day:TrainingDay){
        deleteDayById(day.id)
        insert(day)
    }
}