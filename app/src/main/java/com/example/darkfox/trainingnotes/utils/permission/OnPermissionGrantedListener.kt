package com.example.darkfox.trainingnotes.utils.permission

interface OnPermissionGrantedListener {
    fun onPermissionGranted(write:Boolean,read:Boolean)
}