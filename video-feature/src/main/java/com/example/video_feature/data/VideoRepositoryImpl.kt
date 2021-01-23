package com.example.video_feature.data

import com.example.video_feature.domain.VideoRepository

class VideoRepositoryImpl(private val dataSource: VideoDataSource): VideoRepository {

    override fun getVideo(): Int = dataSource.getVideo()
}