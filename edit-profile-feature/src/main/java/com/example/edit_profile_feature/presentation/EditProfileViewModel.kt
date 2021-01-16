package com.example.edit_profile_feature.presentation

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import com.example.base.mvvm.BaseViewModel
import com.example.data.RepositoryImpl
import com.example.domain.entity.UserEntity
import com.example.edit_profile_feature.navigator.EditProfileNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

class EditProfileViewModel : BaseViewModel() {

    var user: MutableLiveData<UserEntity> = MutableLiveData()
    private var newUser = UserEntity()
    private lateinit var repository: RepositoryImpl
    private lateinit var navigator: EditProfileNavigator

    fun onViewCreated(
        repository: RepositoryImpl,
        navigator: EditProfileNavigator
    ) {
        this.repository = repository
        this.navigator = navigator

        val token = repository.getToken()
        add(repository.getUserById(token.toInt())
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
        repository.updateUser(newUser)
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
    }

    fun onCLickBackButton() {
        navigator.onClickBackFromEditProfile()
    }
}