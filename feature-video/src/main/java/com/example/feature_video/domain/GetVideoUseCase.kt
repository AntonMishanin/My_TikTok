package com.example.feature_video.domain

import javax.inject.Inject

class GetVideoUseCase @Inject constructor(private val repository: VideoRepository) {

    operator fun invoke(): Int = repository.getVideo()
}