package com.example.feature_video.di

import com.example.feature_video.data.VideoRepositoryImpl
import com.example.feature_video.domain.VideoRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindRepository(videoRepositoryImpl: VideoRepositoryImpl): VideoRepository
}