package com.example.settings_feature.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.UiState
import com.example.base.mvvm.BaseViewModel
import com.example.domain.usecase.SetTokenUseCase
import com.example.settings_feature.domain.entity.SettingsEntityUi
import com.example.settings_feature.domain.use_case.GetContentUseCase
import com.example.settings_feature.navigator.SettingsNavigator
import com.example.settings_feature.utils.Constants.Companion.VIEW_TYPE_LOG_OUT
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val setTokenUseCase: SetTokenUseCase,
    private val getContentUseCase: GetContentUseCase
) : BaseViewModel() {

    private lateinit var navigator: SettingsNavigator

    var content = MutableLiveData<List<SettingsEntityUi>>()
    var state = MutableLiveData<UiState>()

    init {
        loadContent()
    }

    private fun loadContent(){
        state.value = UiState.PROGRESS

        add(getContentUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                content.value = it
                state.value = UiState.CONTENT
            }, {
                state.value = UiState.FAIL
                it.printStackTrace()
            })
        )
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