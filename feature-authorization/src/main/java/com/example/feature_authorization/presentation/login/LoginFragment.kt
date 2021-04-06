package com.example.feature_authorization.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization.R
import com.example.feature_authorization.databinding.FragmentLoginBinding
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.shared_base.Navigator
import com.example.shared_utils.showKeyboard
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private var binding: FragmentLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

        val navigator = (requireActivity() as Navigator).provideNavigator() as AuthorizationNavigator

        viewModel.onViewCreated(navigator)
        initView()
        showKeyboard(requireContext())
        observeViewModel()
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initView() {
        binding?.userNameField?.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener { viewModel.enteringUserName(it) }
        }

        binding?.userPasswordField?.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener { viewModel.enteringPassword(it) }
        }

        binding?.loginButton?.setOnClickListener { viewModel.loginUser(resources) }
        binding?.goToRegistration?.setOnClickListener { viewModel.goToRegistration() }
    }

    private fun observeViewModel() {
        viewModel.enableLoginButton.observe(viewLifecycleOwner) { enableLoginButton ->
            binding?.loginButton?.isEnabled = enableLoginButton
        }

        viewModel.showMessageFailLogin.observe(viewLifecycleOwner) { showMessage(it) }
    }

    private fun showMessage(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}