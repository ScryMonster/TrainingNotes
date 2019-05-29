package com.example.darkfox.trainingnotes.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "accounts")
@Parcelize
data class Account(
        @PrimaryKey
        var fireBaseId: String,
        var email: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var imageUrl: String? = null,
        var properties: UserProperties? = null
) : Parcelable