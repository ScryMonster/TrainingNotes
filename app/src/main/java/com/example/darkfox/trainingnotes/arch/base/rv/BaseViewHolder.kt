package com.example.darkfox.trainingnotes.arch.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Cody on 18.06.2018.
 */
abstract class BaseViewHolder<Item>(itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun bind(item:Item, position:Int)
}