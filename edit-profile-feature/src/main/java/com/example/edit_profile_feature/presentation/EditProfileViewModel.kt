package com.example.edit_profile_feature.presentation

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import com.example.base.mvvm.BaseViewModel
import com.example.domain.entity.UserEntity
import com.example.domain.usecase.GetTokenUseCase
import com.example.domain.usecase.GetUserByIdUseCase
import com.example.domain.usecase.UpdateUserUseCase
import com.example.edit_profile_feature.navigator.EditProfileNavigator
import com.example.utils.SingleLiveEvent
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

    fun onViewCreated(navigator: EditProfileNavigator) {
        this.navigator = navigator

        val token = getTokenUseCase()
        add(getUserByIdUseCase(token.toInt())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                user.postValue(it)
                newUser = it
            })
    }

    fun onDestroyView() {
        clear()
    }

    fun userNameChanged(inputUserName: Editable?) {
        newUser.name = inputUserName.toString()
    }

    fun onClickSaveButton() {
        updateUserUseCase(newUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    // Log.d("TAG", "insert onComplete()")
                }

                override fun onError(e: Throwable) {
                    //Log.d("TAG", "insert onError ${e.message}")
                }
            })

        saveEvent("Saved success")
    }

    fun onCLickBackButton() {
        navigator.onClickBackFromEditProfile()
    }
}