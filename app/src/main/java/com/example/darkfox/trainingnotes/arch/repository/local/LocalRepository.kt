package com.example.darkfox.trainingnotes.arch.repository.local

interface  LocalRepository<T> {
    fun save(item:T,
                     success:()->Unit = {},
                     error:(Exception)->Unit = {})


    fun restore(success:(T)->Unit,
                        error:(Exception)->Unit)
}