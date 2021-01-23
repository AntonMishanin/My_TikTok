package com.example.video_feature.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.video_feature.domain.GetVideoUseCase
import com.example.video_feature.navigator.VideoNavigator
import javax.inject.Inject

class RecordVideoViewModel
@Inject constructor(private val getVideoUseCase: GetVideoUseCase) : ViewModel() {

    private var navigator: VideoNavigator? = null

    init {
        val video = getVideoUseCase()
        Log.d("TAG", "video =$video")
    }

    fun onViewCreated(navigator: VideoNavigator) {
        this.navigator = navigator
    }
}