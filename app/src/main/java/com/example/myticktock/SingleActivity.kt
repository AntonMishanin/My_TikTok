package com.example.myticktock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.feature_bottom_navigation.navigator.BottomNavigator
import com.example.feature_edit_profile.navigator.EditProfileNavigator
import com.example.feature_profile.navigator.ProfileNavigator
import com.example.feature_settings.navigator.SettingsNavigator
import com.example.feature_splash.navigation.SplashNavigator
import com.example.feature_video.navigator.VideoNavigator

class SingleActivity : AppCompatActivity(),
    SplashNavigator,
    AuthorizationNavigator,
    BottomNavigator, SettingsNavigator,
    VideoNavigator, EditProfileNavigator, ProfileNavigator {

    private lateinit var navController: NavController

    private lateinit var navBuilder: NavOptions.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        navController = findNavController(R.id.nav_host_fragment)
        navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(R.anim.slide_left).setExitAnim(R.anim.wait)
            .setPopEnterAnim(R.anim.slide_left).setPopExitAnim(R.anim.wait)
    }

    /*
    Splash
     */

    override fun navigateToAuthorization() {
        navController.navigate(R.id.loginFragment, null, navBuilder.build())
    }

    override fun navigateToProfile() {
        navController.navigate(R.id.mainFragment, null, navBuilder.build())
    }

    /*
    Auth
     */

    override fun navigateToLogin() {
        navController.popBackStack()
    }

    override fun navigateToRegistration() {
        navController.navigate(R.id.registrationFragment, null, navBuilder.build())
    }

    /*
    Profile navigation
     */

    override fun navigateToEditProfile() {
        navController.navigate(R.id.editProfileFragment, null, navBuilder.build())
    }

    override fun navigateToSettings() {
        navController.navigate(R.id.settingsFragment, null, navBuilder.build())
    }

    /*
    Bottom navigation
     */

    override fun onClickVideo() {
        navController.navigate(R.id.recordVideoFragment, null, navBuilder.build())
    }

    /*
    Settings
     */

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun onLogOut() {
        navController.navigate(R.id.loginFragment, null, navBuilder.build())
    }

    /*
    Video
     */

    override fun navigateBackFromVideo() {
        navController.popBackStack()
    }

    /*
    EditProfile
     */

    override fun onClickBackFromEditProfile() {
        navController.popBackStack()
    }
}