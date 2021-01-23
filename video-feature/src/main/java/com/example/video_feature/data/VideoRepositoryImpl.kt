package com.example.video_feature.data

import com.example.video_feature.domain.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(private val dataSource: VideoDataSource): VideoRepository {

    override fun getVideo(): Int = dataSource.getVideo()
}