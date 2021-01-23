package com.example.video_feature.di

import android.content.Context
import com.example.video_feature.data.VideoDataSource
import com.example.video_feature.data.VideoRepositoryImpl
import com.example.video_feature.domain.VideoRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideVideoRepository(dataSource: VideoDataSource): VideoRepository = VideoRepositoryImpl(dataSource)

    @Provides
    fun provideVideoDataSource(context: Context): VideoDataSource = VideoDataSource(context)
}