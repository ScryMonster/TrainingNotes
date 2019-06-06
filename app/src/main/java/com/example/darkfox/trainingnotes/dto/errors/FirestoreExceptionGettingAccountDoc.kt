package com.example.darkfox.trainingnotes.dto.errors

class FirestoreExceptionGettingAccountDoc(val failCause:String) : Exception(failCause) {
}