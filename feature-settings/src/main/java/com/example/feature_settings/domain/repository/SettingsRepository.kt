package com.example.feature_settings.domain.repository

import com.example.feature_settings.domain.entity.SettingsEntityUi
import io.reactivex.Single

interface SettingsRepository {

    fun getContent(): Single<List<SettingsEntityUi>>
}