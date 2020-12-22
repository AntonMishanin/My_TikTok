package com.example.myticktock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.authorization_feature.AuthorizationNavigator
import com.example.splash_feature.SplashNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView

class SingleActivity : AppCompatActivity(), SplashNavigator, AuthorizationNavigator {

    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    /*
    Splash
     */

    override fun navigateToAuthorization() {
        navController.navigate(R.id.loginFragment)
    }

    override fun navigateToProfile() {
        navController.navigate(R.id.profileFragment)
    }

    /*
    Auth
     */
    override fun navigateToLogin() {
        navController.navigate(R.id.loginFragment)
    }

    override fun navigateToRegistration() {
        navController.navigate(R.id.registrationFragment)
    }
}