package com.example.feature_bottom_navigation.presentation

import androidx.core.view.ViewCompat.requireViewById
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.feature_bottom_navigation.R
import com.example.feature_bottom_navigation.navigator.BottomNavigator
import com.example.shared_base.NavigatorProvider
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onStart() {
        super.onStart()
        initNavigation()
    }

    private fun initNavigation(){
        val navigator = (requireActivity() as NavigatorProvider).provideNavigator() as BottomNavigator

        bottomNavigationView = requireViewById(requireView(), R.id.bottom_navigation_view)
        navController = requireActivity().findNavController(R.id.bottom_navigation_container)
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.recordVideoFragment -> navigator.goToVideo()
                R.id.profileFragment -> navController.navigate(R.id.profileFragment)
                R.id.newsFeedFragment -> navController.navigate(R.id.newsFeedFragment)
            }
            false
        }
    }
}