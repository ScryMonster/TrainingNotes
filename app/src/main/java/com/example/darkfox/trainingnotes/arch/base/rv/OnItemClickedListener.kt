package com.example.levent.levent.KotlinModifier.arch.base.recycler

/**
 * Created by Tots on 18.06.2018.
 */
interface OnItemClickedListener<Item>{
    fun onItemClick(item:Item,position:Int)
}