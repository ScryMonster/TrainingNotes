package com.example.darkfox.trainingnotes.models.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.darkfox.trainingnotes.R
import com.example.darkfox.trainingnotes.models.dto.gym.Round
import kotlinx.android.synthetic.main.round_details_view.view.*

class RoundDetails @JvmOverloads constructor(context: Context,
                                             attrs: AttributeSet? = null,
                                             defStyleAttributeSet: Int = 0,
                                             defStyleRes: Int = 0) : LinearLayout(context, attrs,defStyleAttributeSet,defStyleRes) {

    init {
        createView(attrs, defStyleRes)
    }

    private fun createView(attrs: AttributeSet?, defStyleRes: Int?){
        View.inflate(context, R.layout.round_details_view,this)
    }


    fun setDetails(round: Round){
        roundWeight.text = resources.getString(R.string.set_weight,round.weight.toString())
        roundRepeats.text = round.repeats.toString()
    }

    fun getDetails() = Round(roundWeight.text.toString().toDouble(),roundRepeats.text.toString().toInt())
}