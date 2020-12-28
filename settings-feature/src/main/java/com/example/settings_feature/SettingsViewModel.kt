package com.example.settings_feature

import androidx.lifecycle.ViewModel
import com.example.data.RepositoryImpl
import com.example.settings_feature.navigator.SettingsNavigator

class SettingsViewModel : ViewModel() {

    private lateinit var repository: RepositoryImpl
    private lateinit var navigator: SettingsNavigator

    fun onViewCreated(repository: RepositoryImpl, navigator: SettingsNavigator) {
        this.repository = repository
        this.navigator = navigator
    }

    fun onClickExitButton() {
        repository.setToken(-1L)
        navigator.onLogOut()
    }

    fun onClickBack() {
        navigator.onClickBack()
    }
}