package com.example.darkfox.trainingnotes.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Permission(val name:String,
                 val granted:Int) : Parcelable