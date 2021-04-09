package com.example.feature_splash.presentation

import androidx.lifecycle.ViewModel
import com.example.shared_domain.usecase.GetTokenUseCase
import com.example.feature_splash.navigation.SplashNavigator
import com.example.shared_data.utils.Constants.Companion.PREF_VALUE_TOKEN_NOT_VALID
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    fun checkToken(navigator: SplashNavigator) =
        if (getTokenUseCase() == PREF_VALUE_TOKEN_NOT_VALID) {
            navigator.goToLoginSplash()
        } else {
            navigator.goToProfile()
        }
}