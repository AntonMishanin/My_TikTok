package com.example.feature_video.data.source

import android.content.Context
import com.example.feature_video.data.VideoDataSource
import javax.inject.Inject

class VideoDataSourceImpl @Inject constructor(private val context: Context):
    VideoDataSource {

   override fun getVideo(): Int = 2
}