package com.example.feature_settings.data

import com.example.feature_settings.domain.entity.SettingsEntityData
import com.example.feature_settings.domain.entity.SettingsEntityUi
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun convertToEntityUi(inputContent: SettingsEntityData): SettingsEntityUi =
        SettingsEntityUi(viewType = inputContent.view_type, text = inputContent.text)
}