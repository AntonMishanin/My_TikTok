package com.example.authorization_feature.login

import androidx.lifecycle.ViewModel
import com.example.authorization_feature.AuthorizationNavigator
import com.example.data.RepositoryImpl

class LoginViewModel : ViewModel() {

    private lateinit var repository: RepositoryImpl
    private lateinit var navigator: AuthorizationNavigator

    fun onViewCreated(repository: RepositoryImpl, navigator: AuthorizationNavigator) {
        this.repository = repository
        this.navigator = navigator
    }

    fun onClickLogin() {
        val token = 34 * 22
        repository.setToken(token)
        navigator.navigateToProfile()
    }

    fun onClickRegistration() {
        navigator.navigateToRegistration()
    }
}