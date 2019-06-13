package com.example.darkfox.trainingnotes.dto.gym

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

@Parcelize
class WarmUp(val id:Int = Random.nextInt(),
             var name:String = "",
             var time:String = "") : Parcelable