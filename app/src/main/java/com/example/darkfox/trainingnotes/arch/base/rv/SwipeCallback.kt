package com.example.darkfox.trainingnotes.arch.base.rv

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private var icon: Drawable? = null
    private var backGround: ColorDrawable? = null
    private var swipeListener: (Int) -> Unit = {}

    init {
        backGround = ColorDrawable(Color.RED)
    }

    fun setIcon(icon: Drawable) = apply {
        this.icon = icon
    }

    fun setSwipeListener(listener: (Int) -> Unit) = apply {
        swipeListener = listener
    }


    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        swipeListener(viewHolder.adapterPosition)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val backgroundCornerOffset = -2
        val iconMargin = (itemView.height - (icon?.intrinsicHeight ?: 0)) / 2
        val iconTop = itemView.top + (itemView.height - (icon?.intrinsicHeight ?: 0)) / 2
        val iconBottom = iconTop + (icon?.intrinsicHeight ?: 0)
        if (dX < 0) {
            val iconLeft = itemView.right - iconMargin - (icon?.intrinsicWidth ?: 0)
            val iconRight = itemView.right - iconMargin
            icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            backGround?.setBounds(itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top, itemView.right, itemView.bottom)
        } else {
            icon?.setBounds(0, 0, 0, 0)
            backGround?.setBounds(0, 0, 0, 0)
        }
        backGround?.draw(c)
        icon?.draw(c)
    }
}

