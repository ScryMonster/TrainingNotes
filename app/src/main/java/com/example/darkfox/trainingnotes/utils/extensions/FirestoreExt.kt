package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.dto.Result
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.NullPointerException

fun FirebaseFirestore.users() = collection("users")

fun FirebaseFirestore.days() = collection("days")

infix fun CollectionReference.account(name: String) = document(name)

infix fun CollectionReference.newTrainingDay(day:Long) = document(day.toString())

suspend fun <T> Task<T>.check(success: (T) -> Unit, fail: (Exception) -> Unit) {
    try {
        val await = this.await()
        if (await == null) {
            fail(NullPointerException())
        } else success(await)
    } catch (e: Exception) {
        fail(e)
    }
}

suspend fun <T> Task<T>.checkSusp(success: suspend (T) -> Unit, fail: (Exception) -> Unit) {
    try {
        val await = this.await()
        if (await == null) {
            fail(NullPointerException())
        } else success(await)
    } catch (e: Exception) {
        fail(e)
    }
}


suspend fun <T> Task<T>.check(success: () -> Unit, fail: (Exception) -> Unit) {
    try {
        this.await()
        success()
    } catch (e: Exception) {
        fail(e)
    }
}

suspend fun <T> Task<T>.awaitWithNull(): T? =
        try {
            this.await()
        } catch (e: Exception) {
            null
        }

suspend fun <T : Any> Task<T>.getResults(): Result<T> {
    lateinit var res: Result<T>
    res = try {
        val await = this.await()
        Result.Success(await)
    } catch (e: Exception) {
        Result.Fail(e)
    }
    return res
}

suspend fun <T> Task<T>.checkError(fail: (Exception) -> Unit) =
        try {
            this.await()
        } catch (e: Exception) {
            fail(e)
        }



