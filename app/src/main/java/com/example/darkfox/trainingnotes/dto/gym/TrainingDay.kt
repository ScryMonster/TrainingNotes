package com.example.darkfox.trainingnotes.dto.gym

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trainingDays")
class TrainingDay(@PrimaryKey override val id: Long,
                  val accountId:String,
                  override var state: TrainingState = TrainingState.PLANNED,
                  var training: Training? = null) : Day