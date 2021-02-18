package com.example.myticktock.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.authorization_feature.presentation.login.LoginViewModel
import com.example.authorization_feature.presentation.registration.RegistrationViewModel
import com.example.splash_feature.presentation.SplashViewModel
import com.example.base.mvvm.ViewModelFactory
import com.example.bottom_navigation_feature.presentation.news_feed.NewsFeedViewModel
import com.example.feature_profile.presentation.main.ProfileViewModel
import com.example.edit_profile_feature.presentation.EditProfileViewModel
import com.example.feature_profile.presentation.posts.PostsViewModel
import com.example.settings_feature.presentation.SettingsViewModel
import com.example.video_feature.presentation.RecordVideoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindUserViewModel(userViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    abstract fun bindNewsFeedViewModel(newsFeedViewModel: NewsFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun bindEditProfileViewModel(editProfileViewModel: EditProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecordVideoViewModel::class)
    abstract fun bindRecordVideoViewModel(recordVideoViewModel: RecordVideoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(postsViewModel: PostsViewModel): ViewModel
}