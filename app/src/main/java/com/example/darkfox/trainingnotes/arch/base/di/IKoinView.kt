package com.example.darkfox.trainingnotes.arch.base.di

import org.koin.core.KoinContext
import org.koin.core.scope.Scope
import org.koin.standalone.KoinComponent

interface IKoinView : KoinComponent {
    val scopeName: String
    var session:Scope
    fun buildKoinScope()
//    fun destroyKoinScope()
}