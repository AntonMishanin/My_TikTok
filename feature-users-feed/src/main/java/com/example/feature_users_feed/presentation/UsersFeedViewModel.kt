package com.example.feature_users_feed.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_users_feed.domain.entity.ContentEntity
import javax.inject.Inject

class UsersFeedViewModel @Inject constructor() : ViewModel() {

    val content = MutableLiveData<List<ContentEntity>>()

    fun loadContent() {
        content.value = getContent()
    }

    private fun getContent(): List<ContentEntity> {
        val content = ArrayList<ContentEntity>()

        content.add(ContentEntity(0))
        content.add(ContentEntity(1))
        content.add(ContentEntity(2))
        content.add(ContentEntity(3))

        return content
    }
}