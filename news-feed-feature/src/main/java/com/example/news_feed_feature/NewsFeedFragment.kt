package com.example.news_feed_feature

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class NewsFeedFragment: Fragment(R.layout.fragment_news_feed) {

    private val viewModel: NewsFeedViewModule by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}