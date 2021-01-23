package com.example.video_feature.di

import com.example.video_feature.data.VideoRepositoryImpl
import com.example.video_feature.domain.VideoRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindRepository(videoRepositoryImpl: VideoRepositoryImpl): VideoRepository
}