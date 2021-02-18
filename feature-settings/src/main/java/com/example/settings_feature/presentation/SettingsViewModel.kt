package com.example.settings_feature.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.UiState
import com.example.domain.usecase.SetTokenUseCase
import com.example.settings_feature.navigator.SettingsNavigator
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val setTokenUseCase: SetTokenUseCase
) : ViewModel() {

    private lateinit var navigator: SettingsNavigator

    var content = MutableLiveData<List<String>>()
    var state = MutableLiveData(UiState.PROGRESS)

    init {
        content.value = listOf("String1", "String2", "String3", "String4", "String5", "String6")
        state.value = UiState.CONTENT
    }

    fun onViewCreated(navigator: SettingsNavigator) {
        this.navigator = navigator
    }

    fun onItemClick(position: Int) {

    }

    fun onClickExitButton() {
        setTokenUseCase(-1L)
        navigator.onLogOut()
    }

    fun onClickBack() {
        navigator.onClickBack()
    }
}