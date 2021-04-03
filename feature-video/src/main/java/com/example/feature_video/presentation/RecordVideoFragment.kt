package com.example.feature_video.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_video.R
import com.example.feature_video.databinding.FragmentRecordVideoBinding
import com.example.feature_video.navigator.VideoNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RecordVideoFragment : Fragment(R.layout.fragment_record_video) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RecordVideoViewModel by viewModels { viewModelFactory }

    private var binding: FragmentRecordVideoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordVideoBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

        val navigator = requireActivity() as VideoNavigator
        viewModel.setNavigator(navigator)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initView(){
        binding?.buttonBackFromVideo?.setOnClickListener { viewModel.navigateBack() }
    }
}