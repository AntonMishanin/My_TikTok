package com.example.shared_data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.shared_data.utils.Constants.Companion.PREF_KEY_TOKEN
import com.example.shared_data.utils.Constants.Companion.PREF_VALUE_TOKEN_NOT_VALID
import javax.inject.Inject

class SharedPref @Inject constructor(private var sharedPreferences: SharedPreferences) {

    fun setToken(token: Long) = sharedPreferences.edit { putLong(PREF_KEY_TOKEN, token) }

    fun getToken(): Long = sharedPreferences.getLong(PREF_KEY_TOKEN, PREF_VALUE_TOKEN_NOT_VALID)
}