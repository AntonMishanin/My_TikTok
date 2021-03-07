package com.example.feature_settings.domain.use_case

import com.example.feature_settings.domain.entity.SettingsEntityUi
import com.example.feature_settings.domain.repository.SettingsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetContentUseCase @Inject constructor(private val repository: SettingsRepository) {

    operator fun invoke(): Single<List<SettingsEntityUi>> = repository.getContent()
}