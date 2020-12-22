package com.example.splash_feature

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.data.RepositoryImpl
import com.example.data.preferences.SharedPref

class SplashFragment: Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as SplashNavigator

        val sharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
        val sharedPref = SharedPref(sharedPreferences)
        val repository = RepositoryImpl(sharedPref)

        viewModel.onViewCreated(repository, navigator)
    }
}