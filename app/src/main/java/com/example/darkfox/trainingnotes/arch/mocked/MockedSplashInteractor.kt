package com.example.darkfox.trainingnotes.arch.mocked

import android.app.Activity
import com.example.darkfox.trainingnotes.arch.repository.local.LocalRepository
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission
import com.example.darkfox.trainingnotes.utils.permission.PermissionHelper
import kotlinx.coroutines.delay

class MockedSplashInteractor(private val localAccountRepository: LocalRepository<Account>,
                             private val permissionLocalRepository: LocalRepository<ReadWriteStoragePermission>,
                             private val permissionHelper: PermissionHelper) : ISplashMockedInteractor {

    override suspend fun loadUser(success: (Account) -> Unit, error: (Exception) -> Unit) {
        delay(2000)
        localAccountRepository.restore(success, error)
    }

    override fun savePermissions(permission: ReadWriteStoragePermission) {
        permissionLocalRepository.save(permission)
    }

    override fun attemptRequestPermissions(activity: Activity, success: () -> Unit) {
        permissionHelper
//                .addOnPermissionGrantedListener { write, read ->
//                    permissionLocalRepository.create(ReadWriteStoragePermission(write, read))
//                }
                .addDefaultListener {
                    success()
                }
                .attemptRequestPermissions(activity)
    }

    override fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray, permissions: Array<String>) {
        permissionHelper.onRequestPermissionsResult(requestCode, grantResults,permissions)
    }
}