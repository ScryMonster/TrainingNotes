package com.example.darkfox.trainingnotes.dto.gym

import android.os.Parcelable
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Training(val id:Long,
                    val name:String,
                    val ownWeight:Double,
                    val muscules:List<MuscleGroups>,
                    val warmUP:List<WarmUp> = emptyList(),
                    val exercises:List<Exercise>,
                    val from:Long,
                    val to:Long) : Parcelable