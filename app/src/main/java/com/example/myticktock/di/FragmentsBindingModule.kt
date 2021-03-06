package com.example.myticktock.di

import com.example.authorization_feature.presentation.login.LoginFragment
import com.example.authorization_feature.presentation.registration.RegistrationFragment
import com.example.bottom_navigation_feature.presentation.news_feed.NewsFeedFragment
import com.example.feature_profile.presentation.main.ProfileFragment
import com.example.edit_profile_feature.presentation.EditProfileFragment
import com.example.feature_profile.presentation.posts.PostsFragment
import com.example.settings_feature.view.SettingsFragment
import com.example.splash_feature.presentation.SplashFragment
import com.example.video_feature.presentation.RecordVideoFragment
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
    abstract fun newsFeedFragment(): NewsFeedFragment

    @ContributesAndroidInjector
    abstract fun editProfileFragment(): EditProfileFragment

    @ContributesAndroidInjector
    abstract fun recordVideoFragment(): RecordVideoFragment

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun postsFragment(): PostsFragment
}