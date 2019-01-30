package com.example.darkfox.trainingnotes.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Account(
        val id:Int,
        val email:String,
        val firstName:String,
        val lastName:String,
        val imageUrl:String
):Parcelable