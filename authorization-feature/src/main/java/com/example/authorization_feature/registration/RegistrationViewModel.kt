package com.example.authorization_feature.registration

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.authorization_feature.AuthorizationNavigator
import com.example.data.RepositoryImpl
import com.example.domain.entity.UserEntity

class RegistrationViewModel : ViewModel() {

    private lateinit var repository: RepositoryImpl
    private lateinit var navigator: AuthorizationNavigator

    private val user = UserEntity()

    fun onViewCreated(repository: RepositoryImpl, navigator: AuthorizationNavigator) {
        this.repository = repository
        this.navigator = navigator
    }

    fun onClickLogin() {
        navigator.navigateToLogin()
    }

    fun onClickRegistration() {
        repository.insertUser(user) {
            repository.setToken(it)
            navigator.navigateToProfile()
        }
    }

    fun userNameChanged(userName: Editable?) {
        user.name = userName.toString()
    }

    fun passwordChanged(password: Editable?) {
        user.password = password.toString()
    }
}