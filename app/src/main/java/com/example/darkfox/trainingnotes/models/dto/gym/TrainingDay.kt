package com.example.darkfox.trainingnotes.models.dto.gym

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trainingDays")
data class TrainingDay(@PrimaryKey val id: Long = -1L,
                  val accountId:String = "",
                  var trainings: List<Training> = emptyList())
