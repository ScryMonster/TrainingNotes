package com.example.darkfox.trainingnotes.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReadWriteStoragePermission(
        val writePermissionGranted:Boolean = false,
        val readPermissionGranted:Boolean = false
) : Parcelable