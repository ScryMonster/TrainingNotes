package com.example.darkfox.trainingnotes.dto.gym

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exercise(val name:String,
                    val rounds:List<Round>) : Parcelable