package com.example.feature_authorization.presentation.login

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.shared_base.mvvm.BaseViewModel
import com.example.shared_domain.usecase.GetUserByNameUseCase
import com.example.shared_domain.usecase.SetTokenUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : BaseViewModel() {

    val enableLoginButton = MutableLiveData(false)

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
        toggleEnableLoginButton()
    }

    fun enteringPassword(inputPassword: Editable?) {
        password = inputPassword.toString()
        toggleEnableLoginButton()
    }

    private fun toggleEnableLoginButton(){
        enableLoginButton.value = userName != "" && password != ""
    }
}