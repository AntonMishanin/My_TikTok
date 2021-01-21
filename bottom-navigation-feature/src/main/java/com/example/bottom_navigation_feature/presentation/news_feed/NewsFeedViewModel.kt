package com.example.bottom_navigation_feature.presentation.news_feed

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetUserByNameUseCase
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(private val getUserByNameUseCase: GetUserByNameUseCase): ViewModel() {
}