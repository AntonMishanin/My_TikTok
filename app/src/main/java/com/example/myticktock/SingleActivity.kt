package com.example.myticktock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shared_base.Navigator
import com.example.shared_base.NavigatorProvider

class SingleActivity : AppCompatActivity(), NavigatorProvider {

    private lateinit var navigator: NavigatorImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        navigator = NavigatorImpl(this)
    }

    override fun provideNavigator(): Navigator = navigator
}