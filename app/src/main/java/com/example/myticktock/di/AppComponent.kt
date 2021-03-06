package com.example.myticktock.di

import android.content.Context
import com.example.data.di.DataComponent
import com.example.myticktock.App
import com.example.settings_feature.di.SettingsComponent
import com.example.video_feature.di.VideoComponent
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