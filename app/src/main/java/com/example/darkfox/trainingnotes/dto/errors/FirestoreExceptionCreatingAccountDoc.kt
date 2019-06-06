package com.example.darkfox.trainingnotes.dto.errors

class FirestoreExceptionCreatingAccountDoc(val failCause:String) : Exception(failCause) {
}