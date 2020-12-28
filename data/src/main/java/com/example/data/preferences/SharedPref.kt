package com.example.data.preferences

import android.content.SharedPreferences

class SharedPref(private var sharedPreferences: SharedPreferences) {

    fun setToken(token: Long) {
        val editor = sharedPreferences.edit()
        editor?.putLong("KEY_TOKEN", token)
        editor?.apply()
    }

    fun getToken(): Long {
        return sharedPreferences.getLong("KEY_TOKEN", -1)
    }
}