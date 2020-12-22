package com.example.data

import com.example.data.preferences.SharedPref

class RepositoryImpl(private val sharedPref: SharedPref) {

    /*
    Splash
     */
    fun getToken(): Int = sharedPref.getToken()

    /*
    Authorization
     */

    fun setToken(token: Int) = sharedPref.setToken(token)
}