package com.example.darkfox.trainingnotes.models.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProperties(var age:Number? = null,
                          var weight:Number? = null) : Parcelable