package com.example.darkfox.trainingnotes.utils.extensions

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IdRes
import com.example.darkfox.trainingnotes.utils.enums.TimePatterns
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*

infix fun TextView.showTextViewsAsMandatory(text: String) {
    setText(Html.fromHtml("<html><body><font size=8></font> $text"))
}

infix fun EditText.showTextViewsAsMandatory(text: String) {
    setText(Html.fromHtml("<html><body><font size=8></body><html></font> $text"))
}

fun ViewGroup.inflate(layout: Int, attachToRoot: Boolean = false) = LayoutInflater.from(context).inflate(layout, this, attachToRoot)

/*
*
* */

infix fun <T : View> View.bind(@IdRes res: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return lazy {
        findViewById(res) as T
    }
}


fun TabLayout.addTabWithCondition(condition: () -> Boolean): TabLayout.Tab? {
    if (condition.invoke()) {
        return newTab().also {
            addTab(it)
        }
    }
    return null
}

fun TabLayout.addTabWithCondition(condition: Boolean, code: (TabLayout.Tab) -> Unit) {
    if (condition) {
        newTab()
                .also { tab ->
                    addTab(tab)
                    code(tab)
                }
    }
}

fun View.visible(){
    visibility = VISIBLE
}

fun View.invisible(){
    visibility = INVISIBLE
}

fun View.gone(){
    visibility = GONE
}

infix fun Long.convertToTime(pattern: TimePatterns) = SimpleDateFormat(pattern.format).format(Date(this))





