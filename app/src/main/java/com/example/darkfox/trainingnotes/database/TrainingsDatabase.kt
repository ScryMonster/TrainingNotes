package com.example.darkfox.trainingnotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.darkfox.trainingnotes.database.converters.*
import com.example.darkfox.trainingnotes.database.dao.AccountDao
import com.example.darkfox.trainingnotes.database.dao.TrainingDaysDao
import com.example.darkfox.trainingnotes.models.dto.Account
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingDay

@Database(entities = [Account::class,TrainingDay::class], version = 1)
@TypeConverters(PropertiestConverter::class,TrainingStateConvertor::class,MuscleGroupsConvertor::class,TrainingConverter::class,TrainingsConverter::class)
abstract class TrainingsDatabase : RoomDatabase() {

    abstract fun getAccountsDao():AccountDao

    abstract fun getTrainingDaysDao() : TrainingDaysDao

    companion object {
        private var INSTANCE: TrainingsDatabase? = null
        private val dbName = "trainingNotes.db"


        fun getInstance(context: Context): TrainingsDatabase? {
            if (INSTANCE == null) {
                INSTANCE = create(context)
            }
            return INSTANCE
        }

        private fun create(context: Context) =
                Room.databaseBuilder(context, TrainingsDatabase::class.java, dbName)
                        .build()
    }
}