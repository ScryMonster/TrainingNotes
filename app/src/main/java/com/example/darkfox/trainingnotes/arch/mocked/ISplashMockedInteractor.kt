package com.example.darkfox.trainingnotes.arch.mocked

import android.app.Activity
import com.example.darkfox.trainingnotes.arch.domain.splash.ISplashInteractor
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission

interface ISplashMockedInteractor {
    suspend fun loadUser(success:(Account)->Unit, error:(Exception)->Unit)

    fun savePermissions(permission: ReadWriteStoragePermission)

    fun attemptRequestPermissions(activity: Activity, success: () -> Unit = {})

    fun onRequestPermissionsResult(requestCode: Int,grantResults: IntArray,permissions:Array<String>)
}