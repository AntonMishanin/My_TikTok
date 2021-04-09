package com.example.myticktock

import android.app.Activity
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

class NavigatorImpl(activity: Activity) : AuthorizationNavigator, SplashNavigator,
    BottomNavigator, SettingsNavigator,
    VideoNavigator, EditProfileNavigator, ProfileNavigator {

    private var navController: NavController = activity.findNavController(R.id.nav_host_fragment)
    private var navBuilder: NavOptions.Builder = NavOptions.Builder()

    init {
        navBuilder.setEnterAnim(R.anim.slide_left).setExitAnim(R.anim.wait)
            .setPopEnterAnim(R.anim.slide_left).setPopExitAnim(R.anim.wait)
    }

    /*
    SplashNavigator
     */

    override fun goToLoginSplash() = goToFragmentById(R.id.loginFragment)

    /*
    AuthorizationNavigator
     */

    override fun goToBackFromRegistration() = goToBack()

    override fun goToRegistration() = goToFragmentById(R.id.registrationFragment)

    override fun goToProfile() = goToFragmentById(R.id.mainFragment)

    /*
    BottomNavigator
     */

    override fun goToVideo() = goToFragmentById(R.id.recordVideoFragment)

    /*
    SettingsNavigator
     */

    override fun goToBackFromSettings() = goToBack()

    override fun goToLoginFromSettings() = goToFragmentById(R.id.loginFragment)

    /*
    VideoNavigator
     */

    override fun goToBackFromVideo() = goToBack()

    /*
    EditProfileNavigator
     */

    override fun goToBackFromEditProfile() = goToBack()

    /*
    ProfileNavigator
     */

    override fun goToEditProfile() = goToFragmentById(R.id.editProfileFragment)

    override fun goToSettings() = goToFragmentById(R.id.settingsFragment)

    /*

     */

    private fun goToBack() {
        navController.popBackStack()
    }

    private fun goToFragmentById(id: Int) {
        navController.navigate(id, null, navBuilder.build())
    }
}