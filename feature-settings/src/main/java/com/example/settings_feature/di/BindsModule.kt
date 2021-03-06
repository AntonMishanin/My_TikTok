package com.example.settings_feature.di

import com.example.settings_feature.data.SettingsRepositoryImpl
import com.example.settings_feature.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository
}