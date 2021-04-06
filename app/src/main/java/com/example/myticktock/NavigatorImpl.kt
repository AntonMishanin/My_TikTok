package com.example.myticktock

import androidx.navigation.NavController
import com.example.feature_authorization.navigator.AuthorizationNavigator
import com.example.shared_base.Navigator

class NavigatorImpl(private val navController: NavController) : Navigator, AuthorizationNavigator {

    override fun navigateToLogin() {
        navController.popBackStack()
    }

    override fun navigateToRegistration() = navController.navigate(R.id.registrationFragment)

    override fun navigateToProfile() = navController.navigate(R.id.mainFragment)

    override fun provideNavigator(): Navigator = NavigatorImpl(navController)
}