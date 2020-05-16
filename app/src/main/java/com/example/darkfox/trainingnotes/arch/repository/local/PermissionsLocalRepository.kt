package com.example.darkfox.trainingnotes.arch.repository.local

import com.example.darkfox.trainingnotes.models.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.IPermissionsManager

class PermissionsLocalRepository(private val permissionsManager: IPermissionsManager) {
    fun save(item: ReadWriteStoragePermission) {
        permissionsManager.storePermissions(item)
    }

    fun restore() : ReadWriteStoragePermission  = permissionsManager.restorePermissions()
}