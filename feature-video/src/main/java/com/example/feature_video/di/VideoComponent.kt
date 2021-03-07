package com.example.feature_video.di

import android.content.Context
import com.example.feature_video.domain.VideoRepository
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