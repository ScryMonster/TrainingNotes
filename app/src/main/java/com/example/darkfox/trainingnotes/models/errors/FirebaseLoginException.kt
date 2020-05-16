package com.example.darkfox.trainingnotes.models.errors

class FirebaseLoginException(val failCause:String = "") : Exception(failCause)