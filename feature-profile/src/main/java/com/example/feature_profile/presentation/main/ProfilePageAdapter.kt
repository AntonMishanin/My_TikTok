package com.example.feature_profile.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.feature_profile.presentation.fake.FakeFragment
import com.example.feature_profile.presentation.posts.PostsFragment
import com.example.feature_profile.utils.Constants.Companion.VIEW_TYPE_FAKE
import com.example.feature_profile.utils.Constants.Companion.VIEW_TYPE_POSTS

class ProfilePageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            VIEW_TYPE_POSTS -> {
                PostsFragment()
            }
            VIEW_TYPE_FAKE -> {
                FakeFragment()
            }
            else -> {
                PostsFragment()
            }
        }
    }
}