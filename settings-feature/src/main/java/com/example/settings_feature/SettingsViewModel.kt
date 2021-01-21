package com.example.settings_feature

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.SetTokenUseCase
import com.example.settings_feature.navigator.SettingsNavigator
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val setTokenUseCase: SetTokenUseCase) : ViewModel() {

    private lateinit var navigator: SettingsNavigator

    fun onViewCreated(navigator: SettingsNavigator) {
        this.navigator = navigator
    }

    fun onClickExitButton() {
        setTokenUseCase(-1L)
        navigator.onLogOut()
    }

    fun onClickBack() {
        navigator.onClickBack()
    }
}