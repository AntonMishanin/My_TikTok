package com.example.myticktock

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.data.di.DaggerDataComponent
import com.example.data.di.DataComponent
import com.example.myticktock.di.DaggerAppComponent
import com.example.video_feature.di.DaggerVideoComponent
import com.example.video_feature.di.VideoComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class App : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .context(applicationContext)
            .dataComponent(provideDataComponent())
            .videoComponent(provideVideoComponent())
            .build()
            .inject(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private fun provideDataComponent(): DataComponent =
        DaggerDataComponent
            .builder()
            .context(applicationContext)
            .build()

    private fun provideVideoComponent(): VideoComponent =
        DaggerVideoComponent
            .builder()
            .context(applicationContext)
            .build()
}