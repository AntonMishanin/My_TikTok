package com.example.video_feature

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class RecordVideoFragment : Fragment(R.layout.fragment_record_video) {

    private val viewModel: RecordVideoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}