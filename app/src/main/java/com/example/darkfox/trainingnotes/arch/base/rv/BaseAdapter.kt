package com.example.darkfox.trainingnotes.arch.base.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.levent.levent.KotlinModifier.arch.base.recycler.IBaseAdapter


/**
 * Created by Tots on 18.06.2018.
 */
abstract class BaseAdapter<VH: RecyclerView.ViewHolder,Item> : RecyclerView.Adapter<VH>(), IBaseAdapter<Item, BaseAdapter<VH, Item>> {

    protected var itemList = arrayListOf<Item>()
    protected var clickListener:(Item)->Unit = {}

    override fun setList(newList: ArrayList<Item>,
                         notify: Boolean,
                         clear:Boolean )  = apply {
        if(clear) itemList.clear()
        itemList.addAll(newList)
        if (notify) notifyDataSetChanged()
    }

    open fun addItem(item: Item){
        itemList.addAll(itemList.union(listOf(item)))
        notifyDataSetChanged()
    }

    open fun setItem(item:Item){
        itemList.clear()
        itemList.add(item)
        notifyDataSetChanged()
    }

    open fun checkListAndAddItem(item: Item){
        if (itemList.isEmpty()) setItem(item)
        else addItem(item)
    }
    override fun getList() = itemList

    override fun getItemCount() = itemList.size

    open fun setListener(listener: (Item)->Unit) = apply{
        this.clickListener = listener
    }


}