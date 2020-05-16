package com.example.darkfox.trainingnotes.models.dto.gym

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Round(val weight:Double = 0.0,
            val repeats:Int = -1) : Parcelable