package com.example.feature_splash.presentation

import androidx.lifecycle.ViewModel
import com.example.shared_domain.usecase.GetTokenUseCase
import com.example.feature_splash.navigation.SplashNavigator
import javax.inject.Inject

class SplashViewModel
@Inject constructor(private val getTokenUseCase: GetTokenUseCase) : ViewModel() {

    fun loadToken(
        navigator: SplashNavigator
    ) {
        val token = getTokenUseCase()

        if (token == -1L) {
          //  navigator.navigateToAuthorization()
        } else {
          //  navigator.navigateToProfile()
        }
    }
}