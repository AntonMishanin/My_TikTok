package com.example.feature_settings.data

import android.content.Context
import android.content.res.AssetManager
import com.example.feature_settings.domain.entity.SettingsEntityData
import com.example.feature_settings.utils.Constants.Companion.CONTENT_FILE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class DataSource @Inject constructor(private val context: Context) {

    fun getContent(): List<SettingsEntityData> {
        val json = getJsonFromAssets(context.assets, CONTENT_FILE_NAME)
        val turnsType = object : TypeToken<List<SettingsEntityData>>() {}.type
        return Gson().fromJson(json, turnsType)
    }

    private fun getJsonFromAssets(assetManager: AssetManager, fileName: String): String {
        return try {
            val inputStream = assetManager.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}