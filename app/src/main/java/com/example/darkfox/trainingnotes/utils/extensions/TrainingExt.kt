package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.dto.gym.Training
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

fun Training.getTime(): String {
    val difference = to - from
    val diffInMinutes = difference / 60000
    return "$diffInMinutes min"
}


fun List<MuscleGroups>.toStringRepresentation(): String {
    val musclesInString = arrayListOf<String>().also { inString ->
        this@toStringRepresentation.forEach {
            inString.add(it.name)
        }
    }
    val builder: StringBuilder = StringBuilder()
    musclesInString.forEach {
        builder.append(it.apply { first().toUpperCase() })
    }
    return builder.toString()
}

fun Training.getDate() : String{
    val formatter = SimpleDateFormat("dd.MM.yy")
    return formatter.format(Date(to))
}