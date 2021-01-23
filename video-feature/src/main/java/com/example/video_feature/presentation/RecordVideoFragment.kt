package com.example.video_feature.presentation

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.video_feature.R
import com.example.video_feature.navigator.VideoNavigator
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as VideoNavigator

        viewModel.onViewCreated(navigator)

        val backButton = requireView().findViewById<Button>(R.id.button_back_from_video)
        backButton.setOnClickListener {
            navigator.onClickBackFromVideo()
        }
    }
}