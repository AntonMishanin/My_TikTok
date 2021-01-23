package com.example.data.di

import android.content.Context
import com.example.domain.repository.Repository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])
interface DataComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): DataComponent
    }

    fun provideRepository(): Repository
}