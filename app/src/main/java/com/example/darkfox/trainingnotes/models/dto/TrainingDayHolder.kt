package com.example.darkfox.trainingnotes.models.dto

import android.os.Parcelable
import com.example.darkfox.trainingnotes.models.dto.gym.Training
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TrainingDayHolder(val dayId:Long?,
                             val training:Training) : Parcelable