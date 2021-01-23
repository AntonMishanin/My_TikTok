package com.example.video_feature.di

import com.example.video_feature.domain.GetVideoUseCase
import com.example.video_feature.domain.VideoRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetVideoUseCase(repository: VideoRepository): GetVideoUseCase =
        GetVideoUseCase(repository)
}