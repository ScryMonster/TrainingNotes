package com.example.darkfox.trainingnotes.utils.extensions

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.darkfox.trainingnotes.arch.base.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.BaseViewHolder
import com.example.levent.levent.KotlinModifier.arch.base.recycler.OnItemClickedListener


fun<VH : BaseViewHolder<Item>,Item> RecyclerView.buildWithAction(adapter: BaseAdapter<VH, Item>, code:(Item) -> Unit){
    this.adapter = adapter
    layoutManager = LinearLayoutManager(context)
    itemAnimator = DefaultItemAnimator()
    adapter.setListener(object : OnItemClickedListener<Item> {
        override fun onItemClick(item: Item, position: Int) {
            code(item)
        }

    })
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