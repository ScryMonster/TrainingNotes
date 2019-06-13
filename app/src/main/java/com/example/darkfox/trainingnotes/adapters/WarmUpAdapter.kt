package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.arch.base.rv.BaseViewHolder
import com.example.darkfox.trainingnotes.dto.gym.WarmUp
import com.example.darkfox.trainingnotes.utils.extensions.findAndReplace
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_warm_up_exercise.view.*

class WarmUpAdapter : BaseAdapter<WarmUpAdapter.WarmUpViewHolder, WarmUp>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WarmUpViewHolder(parent.inflate(R.layout.item_warm_up_exercise))

    override fun onBindViewHolder(holder: WarmUpViewHolder, position: Int) {
        holder.bind(itemList[position], position, clickListener)
    }

    override fun addItem(item: WarmUp) {
        if (itemList.any { it.id == item.id }) {
//            val previousWarmUp = itemList.find { it.id == item.id }
//            val warmUpChanged = previousWarmUp?.apply {
//                name = item.name
//                time = item.time
//            }
//            itemList.indexOf(previousWarmUp).apply {
//                itemList.removeAt(this)
//                itemList.add(this, warmUpChanged!!)
//            }

            itemList.findAndReplace(predicate = { it.id == item.id },
                    map = {
                        it?.name = item.name
                        it?.time = item.time
                    }
            )
            notifyDataSetChanged()
        } else {
            itemList.add(item)
            notifyDataSetChanged()
        }
    }


    class WarmUpViewHolder(view: View) : BaseViewHolder<WarmUp>(view) {
        override fun bind(item: WarmUp, position: Int, listener: (WarmUp) -> Unit) {
            itemView.apply {
                setOnClickListener {
                    listener(item)
                }
                etExerciseWarmUpNameField.setText(item.name)
                etExerciseTimeField.setText(item.time)
            }

        }

    }
}