package com.example.feature_authorization.presentation.login

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization.R
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.shared_utils.showKeyboard
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private var loginButton: Button? = null

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
        val userNameLayout =
            requireView().findViewById<TextInputLayout>(R.id.editText_user_name_login)
        userNameLayout.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener {
                viewModel.enteringUserName(it)
            }
        }

        val password =
            requireView().findViewById<TextInputLayout>(R.id.editText_user_password_login)
        password.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener {
                viewModel.enteringPassword(it)
            }
        }

        loginButton = view?.findViewById(R.id.button_login)
        loginButton?.setOnClickListener {
            viewModel.loginUser(resources)
        }

        val registrationView = view?.findViewById<TextView>(R.id.textView_go_to_registration)
        registrationView?.setOnClickListener {
            viewModel.goToRegistration()
        }
    }

    private fun observeViewModel() {
        viewModel.enableLoginButton.observe(viewLifecycleOwner) { enableLoginButton ->
            loginButton?.isEnabled = enableLoginButton
        }

        viewModel.showMessageFailLogin.observe(viewLifecycleOwner) { showMessage(it) }
    }

    private fun showMessage(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}