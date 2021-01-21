package com.example.settings_feature

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.settings_feature.navigator.SettingsNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SettingsViewModel by viewModels {viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as SettingsNavigator

        viewModel.onViewCreated(navigator)

        setListeners()
    }

    private fun setListeners(){
        val backButton = requireView().findViewById<Button>(R.id.button_back)
        backButton.setOnClickListener{
            viewModel.onClickBack()
        }

        val exitButton = requireView().findViewById<Button>(R.id.button_exit_settings)
        exitButton.setOnClickListener{
            viewModel.onClickExitButton()
        }
    }
}