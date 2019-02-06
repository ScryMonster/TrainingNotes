package com.example.darkfox.trainingnotes.arch.repository.local

import io.reactivex.internal.operators.single.SingleDoOnSuccess

interface  LocalRepository<T> {
    fun save(item:T)
    fun restore(success:(T)->Unit,error:(Exception)->Unit)
}