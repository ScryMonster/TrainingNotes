package com.example.darkfox.trainingnotes.database.converters

import androidx.room.TypeConverter
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MuscleGroupsConvertor {

    @TypeConverter
    fun fromMuscleGroups(groups: List<MuscleGroups>?): String {
        if (groups == null) {
            return ""
        }
        val adapter = object : TypeToken<List<MuscleGroups>>() {}.type
        return Gson().toJson(groups, adapter)
    }

    @TypeConverter
    fun toList(groups: String?): List<MuscleGroups>? {
        if (groups == null) {
            return null
        }
        val adapter = object : TypeToken<List<MuscleGroups>>() {}.type
        return Gson().fromJson(groups, adapter)
    }
}