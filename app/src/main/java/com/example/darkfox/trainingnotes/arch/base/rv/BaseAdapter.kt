package com.example.darkfox.trainingnotes.arch.base

import androidx.recyclerview.widget.RecyclerView
import com.example.levent.levent.KotlinModifier.arch.base.recycler.IBaseAdapter
import com.example.levent.levent.KotlinModifier.arch.base.recycler.OnItemClickedListener


/**
 * Created by Tots on 18.06.2018.
 */
abstract class BaseAdapter<VH: RecyclerView.ViewHolder,Item> : RecyclerView.Adapter<VH>(), IBaseAdapter<Item, BaseAdapter<VH, Item>> {

    protected var itemList = arrayListOf<Item>()
    protected lateinit var clickListener: OnItemClickedListener<Item>

    override fun setList(newList: ArrayList<Item>,
                         notify: Boolean,
                         clear:Boolean )  = apply {
        if(clear) itemList.clear()
        itemList.addAll(newList)
        if (notify) notifyDataSetChanged()
    }

    override fun getList() = itemList

    override fun getItemCount() = itemList.size

    open fun setListener(listener: OnItemClickedListener<Item>) = apply{
        this.clickListener = listener
    }


}