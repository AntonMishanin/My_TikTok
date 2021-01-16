package com.example.authorization_feature.registration

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.authorization_feature.AuthorizationNavigator
import com.example.base.mvvm.BaseViewModel
import com.example.data.RepositoryImpl
import com.example.domain.entity.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RegistrationViewModel : BaseViewModel() {

    private lateinit var repository: RepositoryImpl
    private lateinit var navigator: AuthorizationNavigator

    private val user = UserEntity()

    fun onViewCreated(repository: RepositoryImpl, navigator: AuthorizationNavigator) {
        this.repository = repository
        this.navigator = navigator
    }

    fun onDestroyView(){
        clear()
    }

    fun onClickLogin() {
        navigator.navigateToLogin()
    }

    fun onClickRegistration() {
        add(repository.insertUser(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Long>() {
                override fun onSuccess(t: Long) {
                    repository.setToken(t)
                    navigator.navigateToProfile()
                }

                override fun onError(e: Throwable) {
                }
            })
        )
    }

    fun userNameChanged(userName: Editable?) {
        user.name = userName.toString()
    }

    fun passwordChanged(password: Editable?) {
        user.password = password.toString()
    }
}