package com.example.settings_feature.data

import com.example.settings_feature.domain.entity.SettingsEntityData
import com.example.settings_feature.domain.entity.SettingsEntityUi
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun convertToEntityUi(inputContent: SettingsEntityData): SettingsEntityUi =
        SettingsEntityUi(viewType = inputContent.view_type, text = inputContent.text)
}