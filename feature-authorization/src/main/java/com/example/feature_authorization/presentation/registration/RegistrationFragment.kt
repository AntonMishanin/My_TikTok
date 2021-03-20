package com.example.feature_authorization.presentation.registration

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.feature_authorization.R
import com.example.shared_utils.showKeyboard
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RegistrationViewModel by viewModels { viewModelFactory }

    private var registrationButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val navigator = requireActivity() as AuthorizationNavigator

        viewModel.onViewCreated(navigator)
        initView()
        showKeyboard(requireContext())
        observeViewModel()
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()

    }

    private fun initView() {
        val loginView = view?.findViewById<TextView>(R.id.textView_go_to_log_in)
        loginView?.setOnClickListener {
            viewModel.goToLogin()
        }

        val backView = view?.findViewById<ImageButton>(R.id.imageButton_arrow_back)
        backView?.setOnClickListener {
            viewModel.goToLogin()
        }

        registrationButton = view?.findViewById(R.id.button_registration)
        registrationButton?.setOnClickListener {
            viewModel.registerUser(resources)
        }

        val inputUserName =
            requireView().findViewById<TextInputLayout>(R.id.editText_input_user_name)
        inputUserName.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener {
                viewModel.userNameChanged(it)
            }
        }

        val inputPassword =
            requireView().findViewById<TextInputLayout>(R.id.editText_input_user_password)
        inputPassword.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener {
                viewModel.passwordChanged(it)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.enableRegistrationButton.observe(viewLifecycleOwner) { enableRegistrationButton ->
            registrationButton?.isEnabled = enableRegistrationButton
        }

        viewModel.showMessageFailValidation.observe(viewLifecycleOwner) { showMessage(it) }
    }

    private fun showMessage(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}