package com.example.darkfox.trainingnotes.models.dto.gym

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

@Parcelize
data class Exercise(val id: Int = Random.nextInt(),
                    var name: String = "",
                    var rounds: List<Round> = emptyList()) : Parcelable