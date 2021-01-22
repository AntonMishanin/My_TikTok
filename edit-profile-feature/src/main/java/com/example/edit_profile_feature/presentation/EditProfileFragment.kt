package com.example.edit_profile_feature.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.edit_profile_feature.R
import com.example.edit_profile_feature.navigator.EditProfileNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private var userNameView: EditText? = null
    private var backButton: Button? = null
    private var saveButton: Button? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: EditProfileViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as EditProfileNavigator

        viewModel.onViewCreated(navigator)

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

        viewModel.saveEvent.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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