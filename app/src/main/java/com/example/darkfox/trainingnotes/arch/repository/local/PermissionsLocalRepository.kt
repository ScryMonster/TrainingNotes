package com.example.darkfox.trainingnotes.arch.repository.local

import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.PermissionsManager

class PermissionsLocalRepository(private val permissionsManager: PermissionsManager) {
    fun save(item: ReadWriteStoragePermission) {
        permissionsManager.storePermissions(item)
    }

    fun restore() : ReadWriteStoragePermission  = permissionsManager.restorePermissions()
}