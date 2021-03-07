package com.example.feature_video.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.feature_video.domain.GetVideoUseCase
import com.example.feature_video.navigator.VideoNavigator
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