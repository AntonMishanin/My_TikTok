package com.example.myticktock.di

import android.content.Context
import com.example.shared_data.di.DataComponent
import com.example.myticktock.App
import com.example.feature_settings.di.SettingsComponent
import com.example.feature_video.di.VideoComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        FragmentsBindingModule::class],
    dependencies = [DataComponent::class, VideoComponent::class, SettingsComponent::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun dataComponent(dataComponent: DataComponent): Builder
        fun videoComponent(videoComponent: VideoComponent): Builder
        fun settingsComponent(settingsComponent: SettingsComponent): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}