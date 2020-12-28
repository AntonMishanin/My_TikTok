package com.example.bottom_navigation_feature.presentation.main

import android.os.Bundle
import androidx.core.view.ViewCompat.requireViewById
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bottom_navigation_feature.R
import com.example.bottom_navigation_feature.presentation.navigator.BottomNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navigator = requireActivity() as BottomNavigator

        bottomNavigationView = requireViewById(requireView(), R.id.bottom_navigation_view)
        navController = requireActivity().findNavController(R.id.bottom_navigation_container)
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.recordVideoFragment -> navigator.onClickVideo()
                R.id.profileFragment -> navController.navigate(R.id.profileFragment)
                R.id.newsFeedFragment -> navController.navigate(R.id.newsFeedFragment)
            }
            false
        }
    }
}