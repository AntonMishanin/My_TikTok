package com.example.settings_feature.domain.repository

import com.example.settings_feature.domain.entity.SettingsEntityUi
import io.reactivex.Single

interface SettingsRepository {

    fun getContent(): Single<List<SettingsEntityUi>>
}