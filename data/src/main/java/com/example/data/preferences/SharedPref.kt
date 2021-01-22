package com.example.data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPref(private var sharedPreferences: SharedPreferences) {

    fun setToken(token: Long) = sharedPreferences.edit { putLong("KEY_TOKEN", token) }

    fun getToken(): Long = sharedPreferences.getLong("KEY_TOKEN", -1)
}