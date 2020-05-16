package com.example.darkfox.trainingnotes.models.errors

class FirebaseRegisterException(val failCause:String = "") : Exception(failCause)