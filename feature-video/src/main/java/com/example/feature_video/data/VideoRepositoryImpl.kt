package com.example.feature_video.data

import com.example.feature_video.domain.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(private val dataSource: VideoDataSource): VideoRepository {

    override fun getVideo(): Int = dataSource.getVideo()
}