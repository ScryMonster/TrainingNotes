package com.example.darkfox.trainingnotes.utils.extensions

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.rv.BaseViewHolder


fun<VH : BaseViewHolder<Item>,Item> RecyclerView.buildWithAction(adapter: BaseAdapter<VH, Item>, code:RecyclerView.() -> Unit = {}){
    this.adapter = adapter
    layoutManager = LinearLayoutManager(context)
    itemAnimator = DefaultItemAnimator()
    code()
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