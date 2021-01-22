package com.example.bottom_navigation_feature.presentation.profile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.bottom_navigation_feature.R
import com.example.bottom_navigation_feature.navigator.BottomNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProfileViewModel by viewModels {viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as BottomNavigator

        viewModel.onViewCreated(navigator)

        val userNameView = requireView().findViewById<TextView>(R.id.textView_user_name_profile)
        viewModel.user.observe(viewLifecycleOwner) {
            userNameView.text = it.name
        }

        val settingsButton = requireView().findViewById<Button>(R.id.button_settings)
        settingsButton.setOnClickListener {
            viewModel.onClickSettings()
        }

        val editProfileButton = requireView().findViewById<Button>(R.id.button_edit_profile)
        editProfileButton.setOnClickListener {
            viewModel.onClickEditProfile()
        }
    }

    override fun onDestroyView(){
        viewModel.onDestroyView()
        super.onDestroyView()
    }
}