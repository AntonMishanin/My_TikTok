package com.example.feature_edit_profile.presentation

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import com.example.shared_base.mvvm.BaseViewModel
import com.example.shared_domain.entity.UserEntity
import com.example.shared_domain.usecase.GetTokenUseCase
import com.example.shared_domain.usecase.GetUserByIdUseCase
import com.example.shared_domain.usecase.UpdateUserUseCase
import com.example.feature_edit_profile.navigator.EditProfileNavigator
import com.example.shared_utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : BaseViewModel() {

    var user = MutableLiveData<UserEntity>()

    val saveEvent = SingleLiveEvent<String>()

    private var newUser = UserEntity()
    private lateinit var navigator: EditProfileNavigator

    fun loadUserInformation(navigator: EditProfileNavigator) {
        this.navigator = navigator

        val token = getTokenUseCase()
        add(getUserByIdUseCase(token.toInt())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                user.postValue(it)
                newUser = it
            })
    }

    fun onDestroyView() = clear()

    fun userNameChanged(inputUserName: Editable?) {
        newUser.name = inputUserName.toString()
    }

    fun onClickSaveButton() {
        updateUserUseCase(newUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableCompletableObserver() {
                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }
            })

        saveEvent("Saved success")
    }

    fun onCLickBackButton() = navigator.goToBackFromEditProfile()
}