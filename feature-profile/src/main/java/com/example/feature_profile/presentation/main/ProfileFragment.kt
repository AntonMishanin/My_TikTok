package com.example.feature_profile.presentation.main

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.example.feature_profile.R
import com.example.feature_profile.navigator.ProfileNavigator
import com.google.android.material.tabs.TabLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

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

        val navigator = requireActivity() as ProfileNavigator

        viewModel.onViewCreated(navigator)

        val userNameView = requireView().findViewById<TextView>(R.id.textView_user_name)
        viewModel.user.observe(viewLifecycleOwner) {
            userNameView.text = it.name
        }

        val settingsButton = requireView().findViewById<ImageButton>(R.id.imageButton_settings_profile_toolbar)
        settingsButton.setOnClickListener {
            viewModel.onClickSettings()
        }

        val editProfileButton = requireView().findViewById<Button>(R.id.button_edit_profile)
        editProfileButton.setOnClickListener {
            viewModel.onClickEditProfile()
        }

        initTabs()
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }

    private fun initTabs(){
        tabLayout = view?.findViewById(R.id.tab_layout_new_feed)
        val firstTab = tabLayout?.newTab()?.setText("Posts")
        if (firstTab != null) {
            tabLayout?.addTab(firstTab)
        }
        val secondTab = tabLayout?.newTab()?.setText("Fake")
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

        val pagerAdapter = ProfilePageAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPager = view?.findViewById(R.id.view_pager_new_feed)
        viewPager?.adapter = pagerAdapter
        viewPager?.registerOnPageChangeCallback(pageChangeCallback)
    }
}