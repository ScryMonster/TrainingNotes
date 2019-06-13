package com.example.darkfox.trainingnotes.arch.repository.remote

import com.example.darkfox.trainingnotes.dto.gym.TrainingDay
import com.example.darkfox.trainingnotes.utils.extensions.check
import com.example.darkfox.trainingnotes.utils.extensions.convertIntoDocument
import com.example.darkfox.trainingnotes.utils.extensions.days
import com.example.darkfox.trainingnotes.utils.extensions.newTrainingDay
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class TrainingDaysRepository(private val db: FirebaseFirestore) {

    suspend fun saveTrainingDay(day: TrainingDay,success:()->Unit,fail:(Exception)->Unit){
        db.days().newTrainingDay(day.id).set(day).check(success, fail)
    }

    suspend fun getTrainings() = db.days().get()

    suspend fun updateDay(day:TrainingDay,success:()->Unit,fail:(Exception)->Unit){
        db.days().newTrainingDay(day.id).update("trainings",day.trainings.map { it.convertIntoDocument() }).check(success, fail)
    }
}