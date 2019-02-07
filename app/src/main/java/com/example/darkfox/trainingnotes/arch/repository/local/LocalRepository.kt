package com.example.darkfox.trainingnotes.arch.repository.local

import io.reactivex.internal.operators.single.SingleDoOnSuccess

interface  LocalRepository<T> {
    fun save(item:T,
                     success:()->Unit = {},
                     error:(Exception)->Unit = {})


    fun restore(success:(T)->Unit,
                        error:(Exception)->Unit)
}