package com.example.feature_video.data.source

import com.example.feature_video.data.VideoDataSource
import javax.inject.Inject

class VideoDataSourceImpl @Inject constructor(): VideoDataSource {

   override fun getVideo(): Int = 2
}