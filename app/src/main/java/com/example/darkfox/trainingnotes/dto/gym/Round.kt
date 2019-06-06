package com.example.darkfox.trainingnotes.dto.gym

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Round(val weight:Double,
            val count:Int) : Parcelable