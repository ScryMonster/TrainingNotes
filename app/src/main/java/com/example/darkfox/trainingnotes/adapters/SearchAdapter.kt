package com.example.darkfox.trainingnotes.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.arch.base.rv.BaseAdapter
import com.example.darkfox.trainingnotes.models.dto.TrainingDayHolder
import com.example.darkfox.trainingnotes.utils.extensions.inflate
import com.example.darkfox.trainingnotes.utils.extensions.toStringRepresentation
import kotlinx.android.synthetic.main.item_search_suggestion.view.*

class SearchAdapter : BaseAdapter<SearchAdapter.SearchViewHolder,TrainingDayHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchViewHolder(parent.inflate(R.layout.item_search_suggestion))

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int){
        holder.bind(itemList[position],clickListener)
    }


    class SearchViewHolder(view:View) : RecyclerView.ViewHolder(view){
        fun bind(item:TrainingDayHolder,listener:(TrainingDayHolder)->Unit){
            itemView.apply {
                setOnClickListener {
                    listener(item)
                }

                tvSearchedTrainingName.text = item.training.name
                tvSearchedTrainingDate.text = item.training.muscules.toStringRepresentation()
            }
        }
    }
}