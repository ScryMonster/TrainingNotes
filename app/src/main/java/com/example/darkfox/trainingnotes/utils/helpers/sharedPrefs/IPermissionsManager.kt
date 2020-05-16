package com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs

import com.example.darkfox.trainingnotes.models.dto.ReadWriteStoragePermission

interface IPermissionsManager {
    val arePermissionsExist:Boolean

    fun storePermissions(permissions:ReadWriteStoragePermission)

    fun restorePermissions():ReadWriteStoragePermission

    fun clearPermissions()
}