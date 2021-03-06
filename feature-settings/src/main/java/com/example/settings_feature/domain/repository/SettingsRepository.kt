package com.example.settings_feature.domain.repository

import com.example.settings_feature.domain.entity.SettingsEntityUi

interface SettingsRepository {

    fun getContent(): List<SettingsEntityUi>
}