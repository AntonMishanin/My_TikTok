package com.example.bottom_navigation_feature.presentation.news_feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.bottom_navigation_feature.R
import com.google.android.material.tabs.TabLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewsFeedFragment : Fragment(R.layout.fragment_news_feed) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: NewsFeedViewModel by viewModels { viewModelFactory }

    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            tabLayout?.selectTab(tabLayout?.getTabAt(position))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tabLayout = view?.findViewById(R.id.tab_layout_new_feed)
        val firstTab = tabLayout?.newTab()?.setText("First")
        if (firstTab != null) {
            tabLayout?.addTab(firstTab)
        }
        val secondTab = tabLayout?.newTab()?.setText("Second")
        if (secondTab != null) {
            tabLayout?.addTab(secondTab)
        }

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager?.currentItem = tab?.position?:0
            }
        })

        val pagerAdapter = FixedTabsPageAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPager = view?.findViewById(R.id.view_pager_new_feed)
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(pageChangeCallback)
    }

    override fun onDestroyView() {
        viewPager?.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroyView()
    }
}