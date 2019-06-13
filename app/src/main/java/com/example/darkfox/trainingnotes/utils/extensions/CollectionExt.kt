package com.example.darkfox.trainingnotes.utils.extensions

fun <T:Any> ArrayList<T>.findAndReplace(predicate: (T) -> Boolean,map:(T?)->Unit){
    val previous = find(predicate)
    val indexOf = indexOf(previous)
    map(previous)
    removeAt(indexOf)
    add(indexOf,previous!!)
}