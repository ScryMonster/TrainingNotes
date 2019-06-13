package com.example.darkfox.trainingnotes.database.converters

import androidx.room.TypeConverter
import com.example.darkfox.trainingnotes.dto.gym.Training
import com.example.darkfox.trainingnotes.dto.gym.TrainingDay
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TrainingsConverter {

    @TypeConverter
    fun fromTraining(trainings: List<Training>?): String? {
        if (trainings == null) {
            return null
        }
        val adapter = object : TypeToken<List<Training>>() {}.type
        return Gson().toJson(trainings, adapter)
    }

    @TypeConverter
    fun toTraining(trainings: String?): List<Training>? {
        if (trainings == null) {
            return null
        }
        val adapter = object : TypeToken<List<Training>>() {}.type
        return Gson().fromJson(trainings, adapter)
    }
}