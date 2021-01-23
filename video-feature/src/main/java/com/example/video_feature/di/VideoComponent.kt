package com.example.video_feature.di

import android.content.Context
import com.example.video_feature.data.VideoDataSource
import com.example.video_feature.data.source.VideoDataSourceImpl
import com.example.video_feature.domain.VideoRepository
import dagger.BindsInstance
import dagger.Component


@Component(modules = [BindsModule::class, DataModule::class])
interface VideoComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): VideoComponent
    }

    fun provideVideoRepository(): VideoRepository
}