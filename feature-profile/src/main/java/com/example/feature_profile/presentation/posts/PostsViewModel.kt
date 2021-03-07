package com.example.feature_profile.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shared_base.UiState
import javax.inject.Inject

class PostsViewModel @Inject constructor() : ViewModel() {

    var state = MutableLiveData(UiState.PROGRESS)

    fun loadPosts() {
        state.value = UiState.PROGRESS
        //requestData
        state.value = UiState.EMPTY_CONTENT
    }
}