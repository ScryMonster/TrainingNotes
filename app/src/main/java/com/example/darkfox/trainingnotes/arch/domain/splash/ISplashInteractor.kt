package com.example.darkfox.trainingnotes.arch.domain.splash

import android.app.Activity
import com.example.darkfox.trainingnotes.dto.Account
import com.example.darkfox.trainingnotes.dto.ReadWriteStoragePermission

interface ISplashInteractor {

    fun loadUser(success:(Account)->Unit,error:(Exception)->Unit)

    fun savePermissions(permission: ReadWriteStoragePermission)

    fun attemptRequestPermissions(activity: Activity,success: () -> Unit = {})

    fun onRequestPermissionsResult(requestCode: Int,grantResults: IntArray)
}