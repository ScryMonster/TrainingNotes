package com.example.darkfox.trainingnotes.dto.gym.states

import android.widget.LinearLayout
import com.example.darkfox.trainingnotes.R

class Planned : State {
    override fun execute(layout: LinearLayout) {
        layout.setBackgroundColor(layout.context.resources.getColor(R.color.soft_green))
    }

}