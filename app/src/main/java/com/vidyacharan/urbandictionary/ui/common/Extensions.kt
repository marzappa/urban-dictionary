package com.vidyacharan.urbandictionary.ui.common

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

/**
 * Hide keyboard
 */
fun AppCompatActivity.hideKeyboard() {

    this.currentFocus?.run {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)

        // clear focus from current focus view
        clearFocus()
    }
}