package com.example.bottom_navigation_feature.presentation.profile

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.bottom_navigation_feature.R
import com.example.bottom_navigation_feature.presentation.navigator.BottomNavigator

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as BottomNavigator

        val settingsButton = requireView().findViewById<Button>(R.id.button_settings)
        settingsButton.setOnClickListener {
            navigator.onClickSettings()
        }

        val editProfileButton = requireView().findViewById<Button>(R.id.button_edit_profile)
        editProfileButton.setOnClickListener {
            navigator.onClickEditProfile()
        }
    }
}