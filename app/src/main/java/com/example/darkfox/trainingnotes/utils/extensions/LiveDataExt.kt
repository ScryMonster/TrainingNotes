package com.example.darkfox.trainingnotes.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, code:(T)->Unit){
    observe(owner, Observer<T> { t -> code(t) })
}