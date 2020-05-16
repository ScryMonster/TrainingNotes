package com.example.darkfox.trainingnotes.database.converters

import androidx.room.TypeConverter
import com.example.darkfox.trainingnotes.models.dto.gym.TrainingState

class TrainingStateConvertor {

    @TypeConverter
    fun fromMuscleGroups(state: TrainingState): String = state.name

    @TypeConverter
    fun toTrainingState(state: String?) : TrainingState {
        if (state == null) {
            return TrainingState.EMPTY
        }
        return when{
            state == TrainingState.EMPTY.name -> TrainingState.EMPTY
            state == TrainingState.PASSED.name -> TrainingState.PASSED
            state == TrainingState.FINISHED.name -> TrainingState.FINISHED
            state == TrainingState.PLANNED.name -> TrainingState.PLANNED
            else -> TrainingState.EMPTY
        }
    }
}