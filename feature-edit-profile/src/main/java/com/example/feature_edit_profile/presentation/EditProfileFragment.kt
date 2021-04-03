package com.example.feature_edit_profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_edit_profile.R
import com.example.feature_edit_profile.databinding.FragmentEditProfileBinding
import com.example.feature_edit_profile.navigator.EditProfileNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class EditProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: EditProfileViewModel by viewModels { viewModelFactory }

    private var binding: FragmentEditProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(layoutInflater)
        return binding?.root
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            binding?.userName?.addOnEditTextAttachedListener { layout ->
                layout.editText?.setText(it.name)
            }
        }
        viewModel.saveEvent.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        binding?.userName?.addOnEditTextAttachedListener { layout ->
            layout.editText?.addTextChangedListener {
                viewModel.userNameChanged(it)
            }
        }
        binding?.layoutToolbar?.buttonBack?.setOnClickListener { viewModel.onCLickBackButton() }
        binding?.buttonSave?.setOnClickListener { viewModel.onClickSaveButton() }
    }
}