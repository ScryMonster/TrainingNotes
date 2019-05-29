package com.example.darkfox.trainingnotes.dto.gym

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimpleDay(override val id: Long,
                     override var state: TrainingState = TrainingState.EMPTY) : Day,Parcelable