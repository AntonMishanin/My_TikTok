package com.example.settings_feature.domain.use_case

import com.example.settings_feature.domain.entity.SettingsEntityUi
import com.example.settings_feature.domain.repository.SettingsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetContentUseCase @Inject constructor(private val repository: SettingsRepository) {

    operator fun invoke(): Single<List<SettingsEntityUi>> = repository.getContent()
}