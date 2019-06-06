package com.example.darkfox.trainingnotes.dto.errors

class FirebaseLoginException(val failCause:String = "") : Exception(failCause)