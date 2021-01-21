package com.example.bottom_navigation_feature.presentation.profile

import androidx.lifecycle.MutableLiveData
import com.example.base.mvvm.BaseViewModel
import com.example.data.RepositoryImpl
import com.example.domain.entity.UserEntity
import com.example.domain.usecase.GetTokenUseCase
import com.example.domain.usecase.GetUserByIdUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val getTokenUseCase: GetTokenUseCase, private val getUserByIdUseCase: GetUserByIdUseCase) : BaseViewModel() {

    var user: MutableLiveData<UserEntity> = MutableLiveData()

    fun onViewCreated() {
        val token = getTokenUseCase()
        add(getUserByIdUseCase(token.toInt())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                user.postValue(it)
            })
    }

    fun onDestroyView() {
        clear()
    }
}