package com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs

import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission

interface PermissionsManager {
    val arePermissionsExist:Boolean

    fun storePermissions(permissions:ReadWriteStoragePermission)
    fun restorePermissions():ReadWriteStoragePermission
}