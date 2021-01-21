package com.example.bottom_navigation_feature.presentation.news_feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.bottom_navigation_feature.R
import com.example.bottom_navigation_feature.presentation.profile.ProfileViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewsFeedFragment : Fragment(R.layout.fragment_news_feed) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: NewsFeedViewModel by viewModels {viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}