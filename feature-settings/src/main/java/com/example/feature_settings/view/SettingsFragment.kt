package com.example.feature_settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.shared_base.UiState
import com.example.feature_settings.databinding.FragmentSettingsBinding
import com.example.feature_settings.navigator.SettingsNavigator
import com.example.feature_settings.view_model.SettingsViewModel
import com.example.shared_utils.visible
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SettingsViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: SettingsAdapter

    private var binding: FragmentSettingsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

        val navigator = requireActivity() as SettingsNavigator
        viewModel.setNavigator(navigator)
        initView()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initView() {
        adapter =
            SettingsAdapter(viewModel::onItemClick)
        binding?.recyclerViewContent?.adapter = adapter

       // binding?.toolbar?.button_back?.setOnClickListener { viewModel.navigateBack() }
    }

    private fun observeViewModel() {
        viewModel.content.observe(viewLifecycleOwner) { adapter.content = it }
        viewModel.state.observe(viewLifecycleOwner, ::changeState)
    }

    private fun changeState(state: UiState) {
        binding?.recyclerViewContent?.visible(state == UiState.CONTENT)
        binding?.progressBar?.visible(state == UiState.PROGRESS)
     //  binding?.layoutFail?.root?.visible(state == UiState.FAIL)
    }
}