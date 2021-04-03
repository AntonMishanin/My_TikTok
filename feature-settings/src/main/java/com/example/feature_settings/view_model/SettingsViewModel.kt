package com.example.feature_settings.view_model

import androidx.lifecycle.MutableLiveData
import com.example.shared_base.UiState
import com.example.shared_base.mvvm.BaseViewModel
import com.example.shared_domain.usecase.SetTokenUseCase
import com.example.feature_settings.domain.entity.SettingsEntityUi
import com.example.feature_settings.domain.use_case.GetContentUseCase
import com.example.feature_settings.navigator.SettingsNavigator
import com.example.feature_settings.utils.Constants.Companion.VIEW_TYPE_DELETE_USER
import com.example.feature_settings.utils.Constants.Companion.VIEW_TYPE_LOG_OUT
import com.example.shared_domain.entity.UserEntity
import com.example.shared_domain.usecase.DeleteUserUseCase
import com.example.shared_domain.usecase.GetTokenUseCase
import com.example.shared_domain.usecase.GetUserByIdUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val setTokenUseCase: SetTokenUseCase,
    private val getContentUseCase: GetContentUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : BaseViewModel() {

    private lateinit var navigator: SettingsNavigator

    var content = MutableLiveData<List<SettingsEntityUi>>()
    var state = MutableLiveData<UiState>()

    init {
        loadContent()
    }

    private fun loadContent() {
        state.value = UiState.PROGRESS

        add(
            getContentUseCase()
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

            VIEW_TYPE_DELETE_USER -> {
                deleteUser()
            }
        }
    }

    private fun logOut() {
        setTokenUseCase(-1L)
        navigator.onLogOut()
    }

    private fun deleteUser() {
        val disposable = getUserByIdUseCase(getTokenUseCase().toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { user ->
                userDeletionCompleted(user)
            }
        add(disposable)
    }

    private fun userDeletionCompleted(user: UserEntity) {
        val disposable = deleteUserUseCase(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                logOut()
            }
        add(disposable)
    }

    fun navigateBack() = navigator.navigateBack()
}