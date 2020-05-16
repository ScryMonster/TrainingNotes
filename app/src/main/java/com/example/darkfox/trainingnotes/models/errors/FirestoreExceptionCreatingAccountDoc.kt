package com.example.darkfox.trainingnotes.models.errors

class FirestoreExceptionCreatingAccountDoc(val failCause:String) : Exception(failCause) {
}