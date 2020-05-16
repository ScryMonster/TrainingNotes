package com.example.darkfox.trainingnotes.models.dto.gym

import android.os.Parcelable
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Training(val id:Long = -1L,
                    val name:String = "",
                    val ownWeight:Double = -1.0,
                    val muscules:List<MuscleGroups> = emptyList(),
                    val warmUP:List<WarmUp> = emptyList(),
                    val exercises:List<Exercise> = emptyList(),
                    val from:Long = -1L,
                    val to:Long = -1L,
                    var state: TrainingState = TrainingState.PLANNED) : Parcelable