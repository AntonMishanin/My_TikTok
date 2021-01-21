package com.example.myticktock.di

import android.app.Application
import com.example.data.di.DataComponent
import com.example.data.di.DataModule
import com.example.domain.di.DomainComponent
import com.example.myticktock.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ViewModelModule::class,
        FragmentsBindingModule::class],
    dependencies = [DataComponent::class, DomainComponent::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun dataComponent(dataComponent: DataComponent): Builder
        fun domainComponent(domainComponent: DomainComponent): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}