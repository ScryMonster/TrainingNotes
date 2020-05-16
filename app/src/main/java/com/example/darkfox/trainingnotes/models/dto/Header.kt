package com.example.darkfox.trainingnotes.models.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Header(
        var name:String="",
        var headerValue:String=""
):Parcelable