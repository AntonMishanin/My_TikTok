package com.example.feature_profile.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.feature_profile.databinding.FragmentProfileBinding
import com.example.feature_profile.navigator.ProfileNavigator
import com.example.shared_base.NavigatorProvider
import com.google.android.material.tabs.TabLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    private var binding: FragmentProfileBinding? = null

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding?.tabLayout?.selectTab(binding?.tabLayout?.getTabAt(position))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

        val navigator = (requireActivity() as NavigatorProvider).provideNavigator() as ProfileNavigator

        viewModel.loadUserInformation(navigator)

        initView()
        observeViewModel()
        initTabs()
    }

    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initView() {
        binding?.toolbarProfile?.settings?.setOnClickListener { viewModel.navigateToSettings() }
        binding?.layoutUserInfo?.editProfile?.setOnClickListener { viewModel.navigateToEditProfile() }
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            binding?.layoutUserInfo?.userName?.text = it.name
        }
    }

    private fun initTabs() {
        val firstTab = binding?.tabLayout?.newTab()?.setText("Posts")
        if (firstTab != null) {
            binding?.tabLayout?.addTab(firstTab)
        }
        val secondTab = binding?.tabLayout?.newTab()?.setText("Fake")
        if (secondTab != null) {
            binding?.tabLayout?.addTab(secondTab)
        }

        binding?.tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding?.viewPager?.currentItem = tab?.position ?: 0
            }
        })

        val pagerAdapter = ProfilePageAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding?.viewPager?.adapter = pagerAdapter
        binding?.viewPager?.registerOnPageChangeCallback(pageChangeCallback)
    }
}