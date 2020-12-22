package com.example.data.preferences

import android.content.SharedPreferences

class SharedPref(private var sharedPreferences: SharedPreferences) {

    fun setToken(token: Int) {
        val editor = sharedPreferences.edit()
        editor?.putInt("KEY_TOKEN", token)
        editor?.apply()
    }

    fun getToken(): Int {
        return sharedPreferences.getInt("KEY_TOKEN", -1)
    }
}