package com.example.darkfox.trainingnotes.dto.gym

import android.os.Parcelable
import com.example.darkfox.trainingnotes.utils.enums.MuscleGroups
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Training(val id:Long,
                    val name:String,
                    val ownWeight:Double,
                    val muscules:List<MuscleGroups>,
                    val warmUP:String = "",
                    val exercises:List<Exercise>) : Parcelable