package com.example.feature_video.di

import com.example.feature_video.data.VideoDataSource
import com.example.feature_video.data.source.VideoDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideVideoDataSource(): VideoDataSource =
        VideoDataSourceImpl()
}