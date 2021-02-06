package com.example.bottom_navigation_feature.presentation.news_feed

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FixedTabsPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FirstPageFragment()
            }
            1 -> {
                SecondPageFragment()
            }
            else -> {
                FirstPageFragment()
            }
        }
    }
}