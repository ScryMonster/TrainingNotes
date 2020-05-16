package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.models.dto.gym.*
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
        if (musclesInString.last() != it) builder.append(", ")
    }
    return builder.toString()
}

fun Training.getDate(): String {
    val formatter = SimpleDateFormat("dd.MM.yy")
    return formatter.format(Date(to))
}

fun TrainingDay.getDate(): String {
    val formatter = SimpleDateFormat("E dd.MM.yy")
    return formatter.format(Date(id))
}

fun Training.convertIntoDocument(): Map<String, Any> {
    val doc = hashMapOf<String, Any>()
    doc.apply {
        put("id", id)
        put("name", name)
        put("ownWeight", ownWeight)
        put("muscules", muscules.map { it.toString() })
        put("warmUP", warmUP.map { it.convertIntoDocument() })
        put("exercises", exercises.map { it.convertIntoDocument() })
        put("from", from)
        put("to", to)
        put("state", state.toString())
    }

    return doc
}


//fun Map<String, Any>.fromDocumentToTrainingDay(): TrainingDay {
//    val id = get("id") as Long
//    val name = get("name") as String
//    val ownWeight = get("ownWeight") as Double
//    val muscles = (get("muscules") as List<String>).map { it.toMuscleGroup() }
//    val warmUp = (get("warmUP") as List<Map<String, Any>>).map { it.fromDocumentToWarmUp() }
//    val exercises = get("exercises") as List<Map<String, Any>>
//    val from = get("from") as Long
//    val to = get("to") as Long
//    val state = get("state") as String
//}

fun WarmUp.convertIntoDocument(): Map<String, Any> {
    val doc = hashMapOf<String, Any>()
    doc.apply {
        put("id", id)
        put("name", name)
        put("time", time)

    }
    return doc
}

fun Exercise.convertIntoDocument() : Map<String,Any>{
    val doc = hashMapOf<String, Any>()
    doc.apply {
        put("id", id)
        put("name", name)
        put("rounds", rounds.map { it.convertIntoDocument() })

    }
    return doc
}

fun Round.convertIntoDocument() : Map<String,Any>{
    val doc = hashMapOf<String, Any>()
    doc.apply {
        put("weight", weight)
        put("repeats", repeats)
    }
    return doc
}

fun Map<String, Any>.fromDocumentToWarmUp(): WarmUp {
    val id = get("id") as Int
    val name = get("name") as String
    val time = get("time") as String
    return WarmUp(id, name, time)
}

fun String.toMuscleGroup() = when {
    this == MuscleGroups.Shoulders.name -> MuscleGroups.Shoulders
    this == MuscleGroups.Legs.name -> MuscleGroups.Legs
    this == MuscleGroups.Triceps.name -> MuscleGroups.Triceps
    this == MuscleGroups.Biceps.name -> MuscleGroups.Biceps
    this == MuscleGroups.Back.name -> MuscleGroups.Back
    this == MuscleGroups.ABS.name -> MuscleGroups.ABS
    this == MuscleGroups.Breast.name -> MuscleGroups.Breast
    else -> MuscleGroups.Breast
}

//fun Map<String,Any>.fromDocumentToExercise():Exercise{
//    val id = get("id") as Int
//    val name = get("name") as String
//    val rounds = (get("rounds") as List<Map<String,Any>>)
////    return Exercise(id, name, time)
//}