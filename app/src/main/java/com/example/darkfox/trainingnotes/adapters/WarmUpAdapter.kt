package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.rv.BaseViewHolder
import com.example.darkfox.trainingnotes.dto.gym.WarmUp
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_warm_up_exercise.view.*

class WarmUpAdapter : BaseAdapter<WarmUpAdapter.WarmUpViewHolder,WarmUp>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  WarmUpViewHolder(parent.inflate(R.layout.item_warm_up_exercise))

    override fun onBindViewHolder(holder: WarmUpViewHolder, position: Int) {
        holder.bind(itemList[position],position,clickListener)
    }

    fun addItem(item:WarmUp){
        itemList.add(item)
        notifyDataSetChanged()
    }

    class WarmUpViewHolder(view:View) : BaseViewHolder<WarmUp>(view){
        override fun bind(item: WarmUp, position: Int, listener: (WarmUp) -> Unit) {
            itemView.apply {
                etExerciseNameField.setText(item.name)
                etExerciseTimeField.setText(item.time)
            }

        }

    }
}