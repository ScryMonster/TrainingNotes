package com.example.darkfox.trainingnotes.database.converters

import androidx.room.TypeConverter
import com.example.darkfox.trainingnotes.dto.UserProperties
import com.example.darkfox.trainingnotes.utils.extensions.fromJson
import com.google.gson.Gson

class PropertiestConverter {

    @TypeConverter
    fun fromProperties(properties: UserProperties?): String {
        return if (properties == null) Gson().toJson(UserProperties(0, 0.0)) else Gson().toJson(properties)
    }

    @TypeConverter
    fun fromString(gsonedProperties: String): UserProperties = Gson().fromJson<UserProperties>(gsonedProperties)


}