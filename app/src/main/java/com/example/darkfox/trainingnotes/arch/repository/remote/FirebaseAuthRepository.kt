package com.example.darkfox.trainingnotes.arch.repository.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthRepository(private val auth: FirebaseAuth) {

    fun signIn(email: String,
               password: String,
               success: (FirebaseUser) -> Unit,
               exception: (Exception) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    if (result.user != null) {
                        success(auth.currentUser!!)
                    }
                }
                .addOnFailureListener {
                    exception(it)
                }
    }

    fun register(email: String,
                 password: String,
                 success: (FirebaseUser) -> Unit,
                 exception: (Exception) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    if (result.user != null) {
                        success(auth.currentUser!!)
                    }
                }
                .addOnFailureListener {
                    exception(it)
                }
    }

    fun getUser() = auth.currentUser


}