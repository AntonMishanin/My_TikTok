package com.example.video_feature

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.video_feature.navigator.VideoNavigator

class RecordVideoFragment : Fragment(R.layout.fragment_record_video) {

    private val viewModel: RecordVideoViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as VideoNavigator

        val backButton = requireView().findViewById<Button>(R.id.button_back_from_video)
        backButton.setOnClickListener{
            navigator.onClickBackFromVideo()
        }
    }
}