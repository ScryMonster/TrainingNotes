package com.example.darkfox.trainingnotes.utils.extensions

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.rv.BaseViewHolder


fun<VH : RecyclerView.ViewHolder,Item> RecyclerView.buildWithAction(adapter: BaseAdapter<VH, Item>, code:RecyclerView.() -> Unit = {}){
    this.adapter = adapter
    layoutManager = LinearLayoutManager(context)
    itemAnimator = DefaultItemAnimator()
    code()
}

fun RecyclerView.buildWithBaseLayoutManagerAndAnimator(layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context),
                                                       itemAnimator: RecyclerView.ItemAnimator = DefaultItemAnimator(),
                                                       parts: RecyclerView.() -> Unit = {}) {
    this.layoutManager = layoutManager
    this.itemAnimator = itemAnimator
    parts.invoke(this)
}
infix fun<VH : BaseViewHolder<Item>,Item> RecyclerView.setUPAdapter(adapter: BaseAdapter<VH, Item>){
    this.adapter = adapter
    layoutManager = LinearLayoutManager(context)
    itemAnimator = DefaultItemAnimator()
}


fun RecyclerView.setUP(){
    layoutManager = LinearLayoutManager(context)
    itemAnimator = DefaultItemAnimator()
}