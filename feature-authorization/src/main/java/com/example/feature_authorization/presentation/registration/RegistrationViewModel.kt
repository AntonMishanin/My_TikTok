package com.example.feature_authorization.presentation.registration

import android.content.res.Resources
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import com.example.feature_authorization.R
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.shared_base.mvvm.BaseViewModel
import com.example.shared_domain.entity.UserEntity
import com.example.shared_domain.usecase.InsertUserUseCase
import com.example.shared_domain.usecase.SetTokenUseCase
import com.example.shared_utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegistrationViewModel
@Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : BaseViewModel() {

    val enableRegistrationButton = MutableLiveData(false)
    val showMessageFailValidation = SingleLiveEvent<String>()

    private lateinit var navigator: AuthorizationNavigator

    private val user = UserEntity()

    fun onViewCreated(navigator: AuthorizationNavigator) {
        this.navigator = navigator
    }

    fun onDestroyView() = clear()

    fun goToLogin() = navigator.goToBackFromRegistration()

    fun registerUser(resources: Resources) =
        if (isValidationPassed(userName = user.name)) {
            createNewUser()
        } else {
            showMessageFailValidation(resources.getString(R.string.user_name_validation))
        }

    fun userNameChanged(userName: Editable?) {
        user.name = userName.toString()
        toggleEnableRegistrationButton()
    }

    fun passwordChanged(password: Editable?) {
        user.password = password.toString()
        toggleEnableRegistrationButton()
    }

    private fun toggleEnableRegistrationButton() {
        enableRegistrationButton.value = user.name != "" && user.password != ""
    }

    private fun isValidationPassed(userName: String): Boolean = userName.split("").size >= 4

    private fun createNewUser() =
        add(
            insertUserUseCase(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {
                        setTokenUseCase(t)
                        navigator.goToProfile()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
}