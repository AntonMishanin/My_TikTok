package com.example.feature_edit_profile.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_edit_profile.R
import com.example.feature_edit_profile.navigator.EditProfileNavigator
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private var userNameView: TextInputLayout? = null
    private var backButton: ImageButton? = null
    private var saveButton: Button? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: EditProfileViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val navigator = requireActivity() as EditProfileNavigator

        viewModel.loadUserInformation(navigator)
        initView()
        observeViewModel()
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            userNameView?.addOnEditTextAttachedListener { layout ->
                layout.editText?.setText(it.name)
            }
        }

        viewModel.saveEvent.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        userNameView = requireView().findViewById(R.id.user_name_edit_profile)
        backButton = requireView().findViewById(R.id.button_back)
        saveButton = requireView().findViewById(R.id.button_save_edit_profile)

        userNameView?.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener {
                viewModel.userNameChanged(it)
            }
        }

        backButton?.setOnClickListener { viewModel.onCLickBackButton() }

        saveButton?.setOnClickListener { viewModel.onClickSaveButton() }
    }
}