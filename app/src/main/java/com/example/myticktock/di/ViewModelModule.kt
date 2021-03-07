package com.example.myticktock.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization.presentation.login.LoginViewModel
import com.example.feature_authorization.presentation.registration.RegistrationViewModel
import com.example.feature_splash.presentation.SplashViewModel
import com.example.shared_base.mvvm.ViewModelFactory
import com.example.feature_users_feed.presentation.UsersFeedViewModel
import com.example.feature_profile.presentation.main.ProfileViewModel
import com.example.feature_edit_profile.presentation.EditProfileViewModel
import com.example.feature_profile.presentation.posts.PostsViewModel
import com.example.feature_settings.view_model.SettingsViewModel
import com.example.feature_video.presentation.RecordVideoViewModel
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
    @ViewModelKey(UsersFeedViewModel::class)
    abstract fun bindNewsFeedViewModel(usersFeedViewModel: UsersFeedViewModel): ViewModel

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