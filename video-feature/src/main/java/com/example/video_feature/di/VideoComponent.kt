package com.example.video_feature.di

import android.app.Application
import android.content.Context
import com.example.domain.di.DomainComponent
import com.example.video_feature.domain.VideoRepository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class])
interface VideoComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): VideoComponent
    }

fun provideRepository(): VideoRepository
}