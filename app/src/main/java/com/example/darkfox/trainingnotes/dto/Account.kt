package com.example.darkfox.trainingnotes.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Account(
         var id:Int,
         var email:String,
         var firstName:String,
         var lastName:String,
         var imageUrl:String
):Parcelable{


    fun editEmail(email: String) = apply { this@Account.email = email }
    fun editName(name: String) = apply { this@Account.firstName = name }
    fun editLastName(lastName: String) = apply { this@Account.lastName = lastName }
}