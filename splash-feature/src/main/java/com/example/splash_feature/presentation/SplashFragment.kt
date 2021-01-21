package com.example.splash_feature.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.splash_feature.R
import com.example.splash_feature.navigation.SplashNavigator
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

   @Inject
   lateinit var viewModelFactory: ViewModelProvider.Factory

   private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as SplashNavigator

        viewModel.onViewCreated(navigator)
    }
}