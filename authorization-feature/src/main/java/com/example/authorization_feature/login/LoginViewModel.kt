package com.example.authorization_feature.login

import android.text.Editable
import com.example.authorization_feature.AuthorizationNavigator
import com.example.base.mvvm.BaseViewModel
import com.example.domain.usecase.GetUserByNameUseCase
import com.example.domain.usecase.SetTokenUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : BaseViewModel() {

    private lateinit var navigator: AuthorizationNavigator

    private var userName = ""
    private var password = ""

    fun onViewCreated(navigator: AuthorizationNavigator) {
        this.navigator = navigator
    }

    fun onDestroyView() {
        clear()
    }

    fun onClickLogin() {
        add(getUserByNameUseCase(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val token = it.id?.toLong() ?: 0L
                setTokenUseCase(token)
                navigator.navigateToProfile()
            })
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