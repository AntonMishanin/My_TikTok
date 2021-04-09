package com.example.feature_settings.navigator

import com.example.shared_base.Navigator


interface SettingsNavigator : Navigator {

    fun goToBackFromSettings()

    fun goToLoginFromSettings()
}