package com.example.levent.levent.KotlinModifier.arch.base.recycler

/**
 * Created by Cody on 18.06.2018.
 */
interface IBaseAdapter<Item,Type> {
    fun setList(list:ArrayList<Item>,notify:Boolean = false,clear:Boolean = true) : Type
    fun getList():List<Item>
}