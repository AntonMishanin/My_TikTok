package com.example.settings_feature

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.data.RepositoryImpl
import com.example.data.database.UserDatabase
import com.example.data.preferences.SharedPref
import com.example.settings_feature.navigator.SettingsNavigator

class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as SettingsNavigator

        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val sharedPref = SharedPref(sharedPreferences)

        val userDatabase =
            UserDatabase.getUserDatabase(requireContext().applicationContext)
        val userDao = userDatabase.getUserDao()

        val repository = RepositoryImpl(sharedPref, userDao)

        viewModel.onViewCreated(repository, navigator)

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