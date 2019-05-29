package com.example.darkfox.trainingnotes.arch.domain.splash

import android.app.Activity
import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.permission.PermissionHelper

class SplashInteractor(private val localRepository: LocalRepository<Account>,
                       private val permissionLocalRepository: LocalRepository<ReadWriteStoragePermission>,
                       private val permissionHelper: PermissionHelper) : ISplashInteractor {

    override fun loadUser(success: (Account) -> Unit, error: (Exception) -> Unit) {
        localRepository.restore(success, error)
    }

    override fun savePermissions(permission: ReadWriteStoragePermission) {
        permissionLocalRepository.save(permission)
    }

    override fun attemptRequestPermissions(activity: Activity, success: () -> Unit) {
        permissionHelper
                .addDefaultListener {
                    success()
                }
                .attemptRequestPermissions(activity)
    }

    override fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray,permissions:Array<String>) {
        permissionHelper.onRequestPermissionsResult(requestCode, grantResults,permissions)
    }


}