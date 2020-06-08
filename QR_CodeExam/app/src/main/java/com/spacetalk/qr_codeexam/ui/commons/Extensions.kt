package com.spacetalk.webviewexam.commons

import android.content.Context
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.keyboardHide() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)