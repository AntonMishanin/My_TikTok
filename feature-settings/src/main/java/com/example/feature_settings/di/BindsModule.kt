package com.example.feature_settings.di

import com.example.feature_settings.data.SettingsRepositoryImpl
import com.example.feature_settings.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository
}