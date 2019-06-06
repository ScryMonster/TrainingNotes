package com.example.darkfox.trainingnotes.dto.errors

class FirebaseRegisterException(val failCause:String = "") : Exception(failCause)