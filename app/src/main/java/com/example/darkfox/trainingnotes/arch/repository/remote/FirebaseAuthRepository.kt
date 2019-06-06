package com.example.darkfox.trainingnotes.arch.repository.remote

import com.example.darkfox.trainingnotes.utils.extensions.awaitWithNull
import com.example.darkfox.trainingnotes.utils.extensions.check
import com.example.darkfox.trainingnotes.utils.extensions.checkSusp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthRepository(private val auth: FirebaseAuth) {

//    suspend fun signIn(email: String,
//               password: String,
//               success: suspend (FirebaseUser) -> Unit,
//               exception: (Exception) -> Unit) {
//        auth.signInWithEmailAndPassword(email, password).checkSusp({success(it.user)},exception)
//    }

    suspend fun signIn(email: String, password: String) = auth.signInWithEmailAndPassword(email, password)


//    fun register(email: String,
//                 password: String,
//                 success: (FirebaseUser) -> Unit,
//                 exception: (Exception) -> Unit) {
//        auth.createUserWithEmailAndPassword(email, password)
//                .addOnSuccessListener { result ->
//                    if (result.user != null) {
//                        success(auth.currentUser!!)
//                    }
//                }
//                .addOnFailureListener {
//                    exception(it)
//                }
//    }

//    suspend fun register(email: String,
//                         password: String,
//                         success: suspend (FirebaseUser) -> Unit,
//                         exception: (Exception) -> Unit) {
//        auth.createUserWithEmailAndPassword(email, password).checkSusp({ success(it.user) }, exception)
//
//    }

    suspend fun register(email: String, password: String) = auth.createUserWithEmailAndPassword(email, password)



    fun getUser() = auth.currentUser


}