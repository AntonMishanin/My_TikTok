package com.example.feature_authorization.navigator

import com.example.shared_base.Navigator

interface AuthorizationNavigator: Navigator {

    fun navigateToLogin()

    fun navigateToRegistration()

    fun navigateToProfile()
}