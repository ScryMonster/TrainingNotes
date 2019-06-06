package com.example.darkfox.trainingnotes.dto.gym

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.darkfox.trainingnotes.dto.Account
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

@Entity(tableName = "weeks", foreignKeys = [ForeignKey(entity = Account::class,
        parentColumns = arrayOf("fireBaseId"),
        childColumns = arrayOf("accountID"),
        onDelete = CASCADE)]
)
class TrainingWeek(
        @PrimaryKey var id: Long,
        val accountID: Long,
        var days: List<Day> = arrayListOf()) {


    @RequiresApi(Build.VERSION_CODES.O)
    fun generateWeek() :List<Day> {
        val locale = Locale.FRANCE
        val TZ = TimeZone.getDefault()
        val midnight = LocalTime.MIDNIGHT
        val firstDayOfWeek: DayOfWeek = WeekFields.of(locale).firstDayOfWeek
        val firstDay = LocalDate.now(TZ.toZoneId()).with(TemporalAdjusters.previousOrSame(firstDayOfWeek))

        val week = arrayListOf<Day>()
        val oneDayInMillis = 86400000
        val firstWeekMidnight = LocalDateTime.of(firstDay, midnight)
        val firsWeekDayMidnightMilliseconds = firstWeekMidnight.atZone(TZ.toZoneId()).toInstant()?.toEpochMilli()
        week.add(SimpleDay(firsWeekDayMidnightMilliseconds!!))
        while (week.size < 7){
            week.add(SimpleDay(firsWeekDayMidnightMilliseconds + oneDayInMillis))
        }

        return week
    }
}