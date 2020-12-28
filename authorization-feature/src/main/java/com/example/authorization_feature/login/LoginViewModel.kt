package com.example.authorization_feature.login

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.authorization_feature.AuthorizationNavigator
import com.example.data.RepositoryImpl

class LoginViewModel : ViewModel() {

    private lateinit var repository: RepositoryImpl
    private lateinit var navigator: AuthorizationNavigator

    private var userName = ""
    private var password = ""

    fun onViewCreated(repository: RepositoryImpl, navigator: AuthorizationNavigator) {
        this.repository = repository
        this.navigator = navigator
    }

    fun onClickLogin() {
        repository.getUserByName(userName) {
            val token = it.id?.toLong() ?: 0L
            repository.setToken(token)
            navigator.navigateToProfile()
        }
    }

    fun onClickRegistration() {
        navigator.navigateToRegistration()
    }

    fun enteringUserName(inputUserName: Editable?) {
        userName = inputUserName.toString()
    }

    fun enteringPassword(inputPassword: Editable?) {
        password = inputPassword.toString()
    }
}