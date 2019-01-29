package com.example.darkfox.trainingnotes.arch.base.di

import org.koin.core.KoinContext
import org.koin.standalone.KoinComponent

interface IKoinView : KoinComponent {
    val koinContext: KoinContext
        get() = getKoin()

    val scopeName: String
    fun buildKoinScope()
    fun destroyKoinScope()
}