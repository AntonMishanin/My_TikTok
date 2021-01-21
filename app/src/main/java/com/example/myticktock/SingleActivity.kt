package com.example.myticktock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.authorization_feature.AuthorizationNavigator
import com.example.bottom_navigation_feature.navigator.BottomNavigator
import com.example.edit_profile_feature.navigator.EditProfileNavigator
import com.example.settings_feature.navigator.SettingsNavigator
import com.example.splash_feature.navigation.SplashNavigator
import com.example.video_feature.navigator.VideoNavigator

class SingleActivity : AppCompatActivity(),
    SplashNavigator, AuthorizationNavigator,
    BottomNavigator, SettingsNavigator,
    VideoNavigator, EditProfileNavigator {


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        navController = findNavController(R.id.nav_host_fragment)
    }

    /*
    Splash
     */

    override fun navigateToAuthorization() {
        navController.navigate(R.id.loginFragment)
    }

    override fun navigateToProfile() {
        navController.navigate(R.id.mainFragment)
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

    /*
    Bottom navigation
     */

    override fun onClickEditProfile() {
        navController.navigate(R.id.editProfileFragment)
    }

    override fun onClickSettings() {
        navController.navigate(R.id.settingsFragment)
    }

    override fun onClickVideo() {
        navController.navigate(R.id.recordVideoFragment)
    }

    /*
    Settings
     */

    override fun onClickBack() {
        navController.popBackStack()
    }

    override fun onLogOut() {
        navController.navigate(R.id.loginFragment)
    }

    /*
    Video
     */

    override fun onClickBackFromVideo() {
        navController.popBackStack()
    }

    /*
    EditProfile
     */

    override fun onClickBackFromEditProfile() {
        navController.popBackStack()
    }
}