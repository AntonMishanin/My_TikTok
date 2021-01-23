package com.example.myticktock

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.data.di.DaggerDataComponent
import com.example.data.di.DataComponent
import com.example.domain.di.DaggerDomainComponent
import com.example.domain.di.DomainModule
import com.example.myticktock.di.DaggerAppComponent
import com.example.video_feature.di.DaggerVideoComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class App : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        val dataComponent = provideDataComponent()
        dataComponent.inject(this)

        val domainComponent = DaggerDomainComponent
            .builder()
            .domainModule(DomainModule(dataComponent.provideRepository()))
            .build()

        val videoComponent = DaggerVideoComponent
            .builder()
            .context(applicationContext)
            .build()

        DaggerAppComponent
            .builder()
            .application(this)
            .dataComponent(dataComponent)
            .domainComponent(domainComponent)
            .videoComponent(videoComponent)
            .build()
            .inject(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private fun provideDataComponent(): DataComponent =
        DaggerDataComponent.builder().application(this).build()
}