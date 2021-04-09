package com.example.feature_authorization.navigator

import com.example.shared_base.Navigator

interface AuthorizationNavigator : Navigator {

    fun goToBackFromRegistration()

    fun goToRegistration()

    fun goToProfile()
}