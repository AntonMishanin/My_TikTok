package com.example.settings_feature.di

import android.content.Context
import com.example.settings_feature.domain.repository.SettingsRepository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [BindsModule::class])
interface SettingsComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): SettingsComponent
    }

    fun provideSettingsRepository(): SettingsRepository
}