package com.example.feature_profile.presentation.main

import androidx.lifecycle.MutableLiveData
import com.example.shared_base.mvvm.BaseViewModel
import com.example.shared_domain.entity.UserEntity
import com.example.shared_domain.usecase.GetTokenUseCase
import com.example.shared_domain.usecase.GetUserByIdUseCase
import com.example.feature_profile.navigator.ProfileNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase
) : BaseViewModel() {

    private lateinit var navigator: ProfileNavigator

    var user: MutableLiveData<UserEntity> = MutableLiveData()

    fun loadUserInformation(navigator: ProfileNavigator) {
        this.navigator = navigator

        val token = getTokenUseCase()
        add(getUserByIdUseCase(token.toInt())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                user.postValue(it)
            })
    }

    fun onDestroyView() = clear()

    fun navigateToSettings() = navigator.goToSettings()

    fun navigateToEditProfile() = navigator.goToEditProfile()
}