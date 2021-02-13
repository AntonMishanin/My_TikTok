package com.example.authorization_feature.presentation.registration

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.authorization_feature.navigator.AuthorizationNavigator
import com.example.authorization_feature.R
import com.example.utils.showKeyboard
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as AuthorizationNavigator

        viewModel.onViewCreated(navigator)

        initListeners()
        showKeyboard(requireContext())

        viewModel.enableRegistrationButton.observe(viewLifecycleOwner) { enableRegistrationButton ->
            registrationButton?.isEnabled = enableRegistrationButton
        }
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()

    }

    private fun initListeners() {
        val loginView = view?.findViewById<TextView>(R.id.textView_go_to_log_in)
        loginView?.setOnClickListener {
            viewModel.onClickLogin()
        }

        val backView = view?.findViewById<ImageButton>(R.id.imageButton_arrow_back)
        backView?.setOnClickListener{
            viewModel.onClickLogin()
        }

        registrationButton = view?.findViewById(R.id.button_registration)
        registrationButton?.setOnClickListener {
            viewModel.onClickRegistration()
        }

        val inputUserName = requireView().findViewById<TextInputLayout>(R.id.editText_input_user_name)
        inputUserName.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener {
                viewModel.userNameChanged(it)
            }
        }

        val inputPassword = requireView().findViewById<TextInputLayout>(R.id.editText_input_user_password)
        inputPassword.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener {
                viewModel.passwordChanged(it)
            }
        }
    }
}