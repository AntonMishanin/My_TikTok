package com.example.feature_settings.view_model

import androidx.lifecycle.MutableLiveData
import com.example.shared_base.UiState
import com.example.shared_base.mvvm.BaseViewModel
import com.example.shared_domain.usecase.SetTokenUseCase
import com.example.feature_settings.domain.entity.SettingsEntityUi
import com.example.feature_settings.domain.use_case.GetContentUseCase
import com.example.feature_settings.navigator.SettingsNavigator
import com.example.feature_settings.utils.Constants.Companion.VIEW_TYPE_LOG_OUT
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

    fun setNavigator(navigator: SettingsNavigator) {
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

    fun navigateBack() {
        navigator.navigateBack()
    }
}