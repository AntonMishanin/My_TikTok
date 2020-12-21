package com.example.myticktock

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

open class App: MultiDexApplication() {


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}