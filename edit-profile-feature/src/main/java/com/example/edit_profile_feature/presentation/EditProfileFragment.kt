package com.example.edit_profile_feature.presentation

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.edit_profile_feature.R
import com.example.edit_profile_feature.navigator.EditProfileNavigator

class EditProfileFragment: Fragment(R.layout.fragment_edit_profile) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as EditProfileNavigator

        val backButton = requireView().findViewById<Button>(R.id.button_back)
        backButton.setOnClickListener{
            navigator.onClickBackFromEditProfile()
        }
    }
}