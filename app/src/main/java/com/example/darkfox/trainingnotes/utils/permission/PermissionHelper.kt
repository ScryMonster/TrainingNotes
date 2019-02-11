package com.example.darkfox.trainingnotes.utils.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionHelper {

    private var grantedListener:(Boolean,Boolean)->Unit = {_,_->

    }


    private val REQUIRED_PERMISSIONS = arrayListOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE)


    fun addOnPermissionGrantedListener(onPermissionGranted:(Boolean,Boolean)->Unit) = apply{
        this.grantedListener = onPermissionGranted
    }

    fun addPermissions(vararg permission: String) = apply {
        permission.forEach {
            REQUIRED_PERMISSIONS.add(it)
        }
    }


    fun attemptRequestPermissions(fragment: Fragment) {
        if (!arePermissionsGranted(fragment.context)) {
            val array = arrayOf<String>()
            fragment.requestPermissions(REQUIRED_PERMISSIONS.toArray(array), REQUEST_CODE)
        } else {
            grantedListener(true,true)
        }
    }


    fun attemptRequestPermissions(activity: Activity) {
        if (!arePermissionsGranted(activity)) {
            val array = arrayOf<String>()
            ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS.toArray(array), REQUEST_CODE)
        } else {
            grantedListener(true,true)
        }
    }

    private fun arePermissionsGranted(context: Context?): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context!!, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE) {
            val results = getPairPermissionsResults(grantResults)
            grantedListener(results.first,results.second)

        }
    }

    private fun getPairPermissionsResults(grantResults: IntArray):Pair<Boolean,Boolean>{
        val list = arrayListOf<Boolean>()
        grantResults.forEach {
            list.add(it == PackageManager.PERMISSION_GRANTED)
        }
        return Pair(list.first(),list.last())
    }

    private fun allGranted(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return grantResults.isNotEmpty()
    }

    companion object {
        private const val REQUEST_CODE = 1000

        fun isPermissionGranted(context: Context, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }

    }
}
