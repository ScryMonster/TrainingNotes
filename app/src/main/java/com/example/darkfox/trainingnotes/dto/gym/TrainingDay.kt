package com.example.darkfox.trainingnotes.dto.gym

class TrainingDay(val id:Long,
                  var state:TrainingState = TrainingState.EMPTY,
                  var training: Training? = null)