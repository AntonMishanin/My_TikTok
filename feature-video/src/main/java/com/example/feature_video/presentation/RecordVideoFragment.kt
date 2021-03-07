package com.example.feature_video.presentation

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_video.R
import com.example.feature_video.navigator.VideoNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RecordVideoFragment : Fragment(R.layout.fragment_record_video) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RecordVideoViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val navigator = requireActivity() as VideoNavigator
        viewModel.setNavigator(navigator)
        initView()
    }

    private fun initView(){
        val backButton = requireView().findViewById<Button>(R.id.button_back_from_video)
        backButton.setOnClickListener {
            viewModel.navigateBack()
        }
    }
}