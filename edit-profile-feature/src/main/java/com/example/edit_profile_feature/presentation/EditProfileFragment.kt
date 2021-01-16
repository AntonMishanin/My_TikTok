package com.example.edit_profile_feature.presentation

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.data.RepositoryImpl
import com.example.data.database.UserDatabase
import com.example.data.preferences.SharedPref
import com.example.edit_profile_feature.R
import com.example.edit_profile_feature.navigator.EditProfileNavigator

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val viewModel: EditProfileViewModel by viewModels()

    private var userNameView: EditText? = null
    private var backButton: Button? = null
    private var saveButton: Button? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as EditProfileNavigator

        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val sharedPref = SharedPref(sharedPreferences)

        val userDatabase =
            UserDatabase.getUserDatabase(requireContext().applicationContext)
        val userDao = userDatabase.getUserDao()

        val repository = RepositoryImpl(sharedPref, userDao)

        viewModel.onViewCreated(repository, navigator)

        userNameView = requireView().findViewById(R.id.editText_user_name_edit_profile)
        backButton = requireView().findViewById(R.id.button_back)
        saveButton = requireView().findViewById(R.id.button_save_edit_profile)

        initObservers()
        initListeners()
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }

    private fun initObservers() {
        viewModel.user.observe(viewLifecycleOwner) {
            userNameView?.setText(it.name)
        }
    }

    private fun initListeners() {
        userNameView?.addTextChangedListener {
            viewModel.userNameChanged(it)
        }

        backButton?.setOnClickListener {
            viewModel.onCLickBackButton()
        }

        saveButton?.setOnClickListener {
            viewModel.onClickSaveButton()
        }
    }
}