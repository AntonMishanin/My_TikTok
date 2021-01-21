package com.example.splash_feature.presentation

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetTokenUseCase
import com.example.splash_feature.navigation.SplashNavigator
import javax.inject.Inject

class SplashViewModel
@Inject constructor(private val getTokenUseCase: GetTokenUseCase) : ViewModel() {

    fun onViewCreated(
        navigator: SplashNavigator
    ) {
        val token = getTokenUseCase()

        if (token == -1L) {
            navigator.navigateToAuthorization()
        } else {
            navigator.navigateToProfile()
        }
    }
}