package com.example.myticktock.di

import com.example.feature_authorization.presentation.login.LoginFragment
import com.example.feature_authorization.presentation.registration.RegistrationFragment
import com.example.feature_users_feed.presentation.UsersFeedFragment
import com.example.feature_profile.presentation.main.ProfileFragment
import com.example.feature_edit_profile.presentation.EditProfileFragment
import com.example.feature_profile.presentation.posts.PostsFragment
import com.example.feature_settings.view.SettingsFragment
import com.example.feature_splash.presentation.SplashFragment
import com.example.feature_video.presentation.RecordVideoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {

    @ContributesAndroidInjector
    abstract fun splashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun registrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun newsFeedFragment(): UsersFeedFragment

    @ContributesAndroidInjector
    abstract fun editProfileFragment(): EditProfileFragment

    @ContributesAndroidInjector
    abstract fun recordVideoFragment(): RecordVideoFragment

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun postsFragment(): PostsFragment
}