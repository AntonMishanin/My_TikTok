package com.example.feature_video.presentation

import androidx.lifecycle.ViewModel
import com.example.feature_video.navigator.VideoNavigator
import javax.inject.Inject

class RecordVideoViewModel @Inject constructor() : ViewModel() {

    private var navigator: VideoNavigator? = null

    fun setNavigator(navigator: VideoNavigator) {
        this.navigator = navigator
    }

    fun navigateBack() = navigator?.navigateBackFromVideo()
}