package com.example.darkfox.trainingnotes.models.errors

class FirestoreExceptionGettingAccountDoc(val failCause:String) : Exception(failCause) {
}