package com.example.darkfox.trainingnotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.darkfox.trainingnotes.database.converters.PropertiestConverter
import com.example.darkfox.trainingnotes.database.dao.AccountDao
import com.example.darkfox.trainingnotes.dto.Account

@Database(entities = [Account::class], version = 0)
@TypeConverters(PropertiestConverter::class)
abstract class TrainingsDatabase : RoomDatabase() {

    abstract fun getAccountsDao():AccountDao

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