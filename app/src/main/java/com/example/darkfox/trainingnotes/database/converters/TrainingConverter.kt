package com.example.darkfox.trainingnotes.database.converters

import androidx.room.TypeConverter
import com.example.darkfox.trainingnotes.models.dto.gym.Training
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TrainingConverter {

    @TypeConverter
    fun fromTraining(training: Training?): String {
        if (training == null) {
            return ""
        }
        val adapter = object : TypeToken<Training>() {}.type
        return Gson().toJson(training, adapter)
    }

    @TypeConverter
    fun toTraining(training: String?): Training? {
        if (training == null) {
            return null
        }
        val adapter = object : TypeToken<Training>() {}.type
        return Gson().fromJson(training, adapter)
    }
}