package com.example.bottom_navigation_feature.presentation.profile

import androidx.lifecycle.MutableLiveData
import com.example.base.mvvm.BaseViewModel
import com.example.data.RepositoryImpl
import com.example.domain.entity.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers

class ProfileViewModel : BaseViewModel() {

    var user: MutableLiveData<UserEntity> = MutableLiveData()

    fun onViewCreated(repository: RepositoryImpl) {
        val token = repository.getToken()
        add(repository.getUserById(token.toInt())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                user.postValue(it)
            })
    }

    fun onDestroyView() {
        clear()
    }
}