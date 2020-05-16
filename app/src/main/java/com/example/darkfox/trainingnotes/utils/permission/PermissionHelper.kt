package com.example.darkfox.trainingnotes.utils.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.darkfox.trainingnotes.models.dto.Permission

class PermissionHelper {

    private var grantedListener:(Boolean,Boolean)->Unit = {_,_->

    }

    private var defaultListener:()->Unit= {

    }


    private val REQUIRED_PERMISSIONS = arrayListOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE)


    fun addOnPermissionGrantedListener(onPermissionGranted:(Boolean,Boolean)->Unit) = apply{
        this.grantedListener = onPermissionGranted
    }

    fun addDefaultListener(listener:()->Unit) = apply {
        defaultListener = listener
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
            defaultListener.invoke()
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

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray,permissions:Array<String>) {
        if (requestCode == REQUEST_CODE){
            val list = grantResults makeListOfPermissionsObjects permissions
            list.forEach {
                if (it.name == Manifest.permission.WRITE_EXTERNAL_STORAGE){
                    Log.i("Permission",it.name)
                }
            }
            defaultListener.invoke()
        }
    }

    companion object {
        private const val REQUEST_CODE = 1000

        fun isPermissionGranted(context: Context, permission: String): Boolean {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }

    }

    private infix fun IntArray.makeListOfPermissionsObjects(names:Array<String>) : List<Permission>{
        val permissions = arrayListOf<Permission>()
        forEachIndexed { index, granted ->
            permissions.add(Permission(names[index],granted))
        }
        return permissions
    }
}
