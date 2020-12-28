package com.example.bottom_navigation_feature.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.RepositoryImpl
import com.example.domain.entity.UserEntity

class ProfileViewModel : ViewModel() {

    var user: MutableLiveData<UserEntity> = MutableLiveData()

    fun onViewCreated(repository: RepositoryImpl) {
        val token = repository.getToken()
        repository.getUserById(token.toInt()){
            user.postValue(it)
        }
    }
}