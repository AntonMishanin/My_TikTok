package com.example.data.di

import android.app.Application
import com.example.domain.di.DomainComponent
import com.example.domain.repository.Repository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])
interface DataComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): DataComponent
    }

    fun inject(app: Application)

    fun provideRepository(): Repository
}