package com.example.feature_authorization.presentation.login

import android.content.res.Resources
import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.feature_authorization.R
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.shared_base.mvvm.BaseViewModel
import com.example.shared_domain.usecase.GetUserByNameUseCase
import com.example.shared_domain.usecase.SetTokenUseCase
import com.example.shared_utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : BaseViewModel() {

    val enableLoginButton = MutableLiveData(false)
    val showMessageFailLogin = SingleLiveEvent<String>()

    private lateinit var navigator: AuthorizationNavigator

    private var userName = ""
    private var password = ""

    fun onViewCreated(navigator: AuthorizationNavigator) {
        this.navigator = navigator
    }

    fun onDestroyView() {
        clear()
    }

    fun loginUser(resources: Resources) {
        add(
            getUserByNameUseCase(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val token = it.id?.toLong() ?: 0L
                    setTokenUseCase(token)
                    navigator.navigateToProfile()
                }, {
                    Log.d("TAG", "it+${it.message}")
                    showMessageFailLogin(resources.getString(R.string.fail_login))
                })
        )
    }

    fun goToRegistration() {
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

    private fun toggleEnableLoginButton() {
        enableLoginButton.value = userName != "" && password != ""
    }
}