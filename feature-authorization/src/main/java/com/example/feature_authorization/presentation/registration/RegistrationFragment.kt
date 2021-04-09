package com.example.feature_authorization.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization.databinding.FragmentRegistrationBinding
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.shared_base.NavigatorProvider
import com.example.shared_utils.showKeyboard
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RegistrationViewModel by viewModels { viewModelFactory }

    private var binding: FragmentRegistrationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

        val navigator = (requireActivity() as NavigatorProvider).provideNavigator() as AuthorizationNavigator

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
        binding?.goToLogIn?.setOnClickListener { viewModel.goToLogin() }
        binding?.backArrow?.setOnClickListener { viewModel.goToLogin() }
        binding?.buttonRegistration?.setOnClickListener { viewModel.registerUser(resources) }

        binding?.inputFieldUserName?.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener { viewModel.userNameChanged(it) }
        }

        binding?.inputFieldUserPassword?.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener { viewModel.passwordChanged(it) }
        }
    }

    private fun observeViewModel() {
        viewModel.enableRegistrationButton.observe(viewLifecycleOwner) { enableRegistrationButton ->
            binding?.buttonRegistration?.isEnabled = enableRegistrationButton
        }

        viewModel.showMessageFailValidation.observe(viewLifecycleOwner) { showMessage(it) }
    }

    private fun showMessage(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}