package com.example.settings_feature

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.settings_feature.navigator.SettingsNavigator

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as SettingsNavigator

        val backButton = requireView().findViewById<Button>(R.id.button_back)
        backButton.setOnClickListener{
            navigator.onClickBack()
        }
    }
}