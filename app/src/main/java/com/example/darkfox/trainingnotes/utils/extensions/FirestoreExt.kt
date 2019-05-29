package com.example.darkfox.trainingnotes.utils.extensions

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

fun FirebaseFirestore.users() = collection("users")

infix fun CollectionReference.newAccount(name:String) = document(name)