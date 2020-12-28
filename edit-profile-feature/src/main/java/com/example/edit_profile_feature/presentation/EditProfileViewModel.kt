package com.example.edit_profile_feature.presentation

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.RepositoryImpl
import com.example.domain.entity.UserEntity

class EditProfileViewModel : ViewModel() {

    var user: MutableLiveData<UserEntity> = MutableLiveData()
    private var newUser = UserEntity()
    private lateinit var repository: RepositoryImpl

    fun onViewCreated(repository: RepositoryImpl) {
        this.repository = repository

        val token = repository.getToken()
        repository.getUserById(token.toInt()) {
            user.postValue(it)
            newUser = it
        }
    }

    fun userNameChanged(inputUserName: Editable?) {
        newUser.name = inputUserName.toString()
    }

    fun onClickSaveButton() {
        repository.updateUser(newUser)
    }
}