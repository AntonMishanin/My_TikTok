package com.example.feature_splash.presentation

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_splash.MyView
import com.example.feature_splash.R
import com.example.feature_splash.navigation.SplashNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

   @Inject
   lateinit var viewModelFactory: ViewModelProvider.Factory

   private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val navigator = requireActivity() as SplashNavigator
        viewModel.loadToken(navigator)

        val myView = view?.findViewById<MyView>(R.id.my_view)
    }
}