package com.example.darkfox.trainingnotes.dto.gym

class TrainingDay(override val id:Long,
                  override var state:TrainingState = TrainingState.PLANNED,
                  var training: Training? = null) : Day