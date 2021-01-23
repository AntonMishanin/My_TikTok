package com.example.video_feature.data.source

import android.content.Context
import com.example.video_feature.data.VideoDataSource
import javax.inject.Inject

class VideoDataSourceImpl @Inject constructor(private val context: Context):
    VideoDataSource {

   override fun getVideo(): Int = 2
}