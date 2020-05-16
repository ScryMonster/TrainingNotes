package com.example.darkfox.trainingnotes.utils.extensions

import com.example.darkfox.trainingnotes.models.dto.Result
import java.util.*

fun <T : Any> Result<T>.checkResult(success: (T) -> Unit,
                                    fail: (Throwable) -> Unit) {
    if (this is Result.Success) {
        success(this.data)
    } else {
        fail((this as Result.Fail).cause)
    }
}


suspend fun <T : Any> Result<T>.checkResultSusp(success: suspend (T) -> Unit,
                                                fail: (Throwable) -> Unit) {
    if (this is Result.Success) {
        success(this.data)
    } else {
        fail((this as Result.Fail).cause)
    }
}

fun todaysMidnight(): Long {
    val calendar = GregorianCalendar().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.timeInMillis
}

val emptyString: String
    get() = ""