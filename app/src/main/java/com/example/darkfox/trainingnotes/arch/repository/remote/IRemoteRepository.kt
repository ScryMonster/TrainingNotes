package com.example.darkfox.trainingnotes.arch.repository.remote

import com.example.darkfox.trainingnotes.utils.enums.AccountPropertyType
import com.google.api.Property

interface IRemoteRepository<T> {

    fun create(data: T, success: () -> Unit, error: (Exception) -> Unit)

    fun get(success: (T) -> Unit, error: (Exception) -> Unit)

    fun <O : Any> overwriteProperty(documentName: String,
                                    propertyType: AccountPropertyType,
                                    newValue: O,
                                    success: () -> Unit,
                                    error: (Exception) -> Unit)

}