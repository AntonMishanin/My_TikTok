package com.example.data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SharedPref @Inject constructor(private var sharedPreferences: SharedPreferences) {

    fun setToken(token: Long) = sharedPreferences.edit { putLong("KEY_TOKEN", token) }

    fun getToken(): Long = sharedPreferences.getLong("KEY_TOKEN", -1)
}