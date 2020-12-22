package com.example.authorization_feature.registration

import androidx.lifecycle.ViewModel
import com.example.authorization_feature.AuthorizationNavigator
import com.example.data.RepositoryImpl

class RegistrationViewModel : ViewModel() {

    private lateinit var repository: RepositoryImpl
    private lateinit var navigator: AuthorizationNavigator

    fun onViewCreated(repository: RepositoryImpl, navigator: AuthorizationNavigator) {
        this.repository = repository
        this.navigator = navigator
    }

    fun onClickLogin() {
        navigator.navigateToLogin()
    }

    fun onClickRegistration() {
        val token = 34*34
        repository.setToken(token)
        navigator.navigateToProfile()
    }
}