package com.example.video_feature.di

import android.content.Context
import com.example.video_feature.data.VideoDataSource
import com.example.video_feature.data.source.VideoDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideVideoDataSource(context: Context): VideoDataSource =
        VideoDataSourceImpl(context)
}