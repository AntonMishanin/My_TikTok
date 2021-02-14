package com.example.feature_profile.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.base.UiState
import javax.inject.Inject

class PostsViewModel @Inject constructor() : ViewModel() {

    var state = MutableLiveData(UiState.PROGRESS)

    fun onViewCreated() {
        state.value = UiState.PROGRESS
        //requestData
        state.value = UiState.EMPTY_CONTENT
    }
}