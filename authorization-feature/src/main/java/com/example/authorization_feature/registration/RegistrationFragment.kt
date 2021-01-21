package com.example.authorization_feature.registration

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.authorization_feature.AuthorizationNavigator
import com.example.authorization_feature.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RegistrationViewModel by viewModels {viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as AuthorizationNavigator

        viewModel.onViewCreated(navigator)

        initListeners()
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }

    private fun initListeners(){
        val loginButton = view?.findViewById<Button>(R.id.button_go_to_login)
        loginButton?.setOnClickListener {
            viewModel.onClickLogin()
        }

        val registrationButton = view?.findViewById<Button>(R.id.button_registration)
        registrationButton?.setOnClickListener {
            viewModel.onClickRegistration()
        }

        val inputUserName = requireView().findViewById<EditText>(R.id.editText_input_user_name)
        inputUserName.addTextChangedListener {
            viewModel.userNameChanged(it)
        }

        val inputPassword = requireView().findViewById<EditText>(R.id.editText_input_user_password)
        inputPassword.addTextChangedListener {
            viewModel.passwordChanged(it)
        }
    }
}