package com.example.feature_settings.data

import com.example.feature_settings.domain.entity.SettingsEntityUi
import com.example.feature_settings.domain.repository.SettingsRepository
import io.reactivex.Single
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataSource: DataSource,
    private val mapper: Mapper
) : SettingsRepository {

    override fun getContent(): Single<List<SettingsEntityUi>> {
        val inputContent = dataSource.getContent()

        val outputContent = ArrayList<SettingsEntityUi>()
        for (i in inputContent.indices) {
            outputContent.add(mapper.convertToEntityUi(inputContent[i]))
        }

        return Single.just(outputContent)
    }
}