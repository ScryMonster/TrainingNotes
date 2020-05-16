package com.example.darkfox.trainingnotes.models.dto.gym.states

import android.widget.LinearLayout
import com.example.darkfox.trainingnotes.R

class Passed : State{
    override fun execute(layout: LinearLayout) {
        layout.setBackgroundColor(layout.context.resources.getColor(R.color.indian_red))
    }

}