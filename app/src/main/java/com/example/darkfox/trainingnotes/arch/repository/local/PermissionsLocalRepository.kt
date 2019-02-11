package com.example.darkfox.trainingnotes.arch.repository.local

import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.helpers.sharedPrefs.PermissionsManager

class PermissionsLocalRepository(private val permissionsManager: PermissionsManager) : LocalRepository<ReadWriteStoragePermission> {
    override fun save(item: ReadWriteStoragePermission, success: () -> Unit, error: (Exception) -> Unit) {
        permissionsManager.storePermissions(item)
        success()
    }

    override fun restore(success: (ReadWriteStoragePermission) -> Unit, error: (Exception) -> Unit) {
        success(permissionsManager.restorePermissions())
    }
}