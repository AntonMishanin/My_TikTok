package com.example.authorization_feature.presentation.registration

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import com.example.authorization_feature.navigator.AuthorizationNavigator
import com.example.base.mvvm.BaseViewModel
import com.example.domain.entity.UserEntity
import com.example.domain.usecase.InsertUserUseCase
import com.example.domain.usecase.SetTokenUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegistrationViewModel
@Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : BaseViewModel() {

    val enableRegistrationButton = MutableLiveData<Boolean>(false)

    private lateinit var navigator: AuthorizationNavigator

    private val user = UserEntity()

    fun onViewCreated(navigator: AuthorizationNavigator) {
        this.navigator = navigator
    }

    fun onDestroyView() {
        clear()
    }

    fun onClickLogin() {
        navigator.navigateToLogin()
    }

    fun onClickRegistration() {
        add(
            insertUserUseCase(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {
                        setTokenUseCase(t)
                        navigator.navigateToProfile()
                    }

                    override fun onError(e: Throwable) {
                    }
                })
        )
    }

    fun userNameChanged(userName: Editable?) {
        user.name = userName.toString()
        toggleEnableRegistrationButton()
    }

    fun passwordChanged(password: Editable?) {
        user.password = password.toString()
        toggleEnableRegistrationButton()
    }

    private fun toggleEnableRegistrationButton(){
        enableRegistrationButton.value = user.name != "" && user.password != ""
    }
}