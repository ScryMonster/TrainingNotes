package com.example.darkfox.trainingnotes.dto.gym.states

import android.widget.LinearLayout
import com.example.darkfox.trainingnotes.R

class Finished : State {
    override fun execute(layout: LinearLayout) {
        layout.setBackgroundColor(layout.context.resources.getColor(R.color.windows_blue))
    }

}