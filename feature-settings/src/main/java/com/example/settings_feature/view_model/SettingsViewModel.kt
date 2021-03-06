package com.example.settings_feature.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.UiState
import com.example.domain.usecase.SetTokenUseCase
import com.example.settings_feature.domain.entity.SettingsEntityUi
import com.example.settings_feature.domain.use_case.GetContentUseCase
import com.example.settings_feature.navigator.SettingsNavigator
import com.example.settings_feature.utils.Constants.Companion.VIEW_TYPE_LOG_OUT
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val setTokenUseCase: SetTokenUseCase,
    getContentUseCase: GetContentUseCase
) : ViewModel() {

    private lateinit var navigator: SettingsNavigator

    var content = MutableLiveData<List<SettingsEntityUi>>()
    var state = MutableLiveData(UiState.PROGRESS)

    init {
        content.value = getContentUseCase()
        state.value = UiState.CONTENT
    }

    fun onViewCreated(navigator: SettingsNavigator) {
        this.navigator = navigator
    }

    fun onItemClick(position: Int) {
        when (content.value?.get(position)?.viewType) {
            VIEW_TYPE_LOG_OUT -> {
                logOut()
            }
        }
    }

    private fun logOut() {
        setTokenUseCase(-1L)
        navigator.onLogOut()
    }

    fun onClickBack() {
        navigator.onClickBack()
    }
}