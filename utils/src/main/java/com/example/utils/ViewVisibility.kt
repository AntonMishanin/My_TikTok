package com.example.utils

import android.view.View

fun View.visible(value: Boolean) {
    if (value) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}