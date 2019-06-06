package com.example.darkfox.trainingnotes.arch.repository.remote

import com.example.darkfox.trainingnotes.utils.enums.AccountPropertyType
import com.google.android.gms.tasks.Task
import com.google.api.Property
import com.google.firebase.firestore.DocumentSnapshot

interface IRemoteRepository<T> {

    suspend fun create(data: T,success:()->Unit, error: (Exception) -> Unit = {})

    fun get(id:String,error: (Exception) -> Unit = {}) : Task<DocumentSnapshot>

    suspend fun <O : Any> overwriteProperty(documentName: String,
                                    propertyType: AccountPropertyType,
                                    newValue: O,
                                    success: () -> Unit,
                                    error: (Exception) -> Unit)

}