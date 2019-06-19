package com.example.darkfox.trainingnotes.utils.extensions

import android.content.Context
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
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

fun View.enable(){
    isEnabled = true
}

fun View.disable(){
    isEnabled = false
}

infix fun Long.convertToTime(pattern: TimePatterns) = SimpleDateFormat(pattern.format).format(Date(this))

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

fun EditText.afterTextChangedWithWatcher(afterTextChanged: (String) -> Unit) : TextWatcher {
    val watcher = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    }
    this.addTextChangedListener(watcher)
    return watcher
}

fun CheckBox.check(){
    isChecked = true
}

fun CheckBox.unCheck(){
    isChecked = false
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}




