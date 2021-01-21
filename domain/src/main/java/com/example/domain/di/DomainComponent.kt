package com.example.domain.di

import android.app.Application
import com.example.domain.repository.Repository
import com.example.domain.usecase.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [AndroidInjectionModule::class, DomainModule::class])
interface DomainComponent {

    @Component.Builder
    interface Builder {

        fun domainModule(domainModule: DomainModule): Builder

        fun build(): DomainComponent
    }

    fun provideDeleteUserUseCase(): DeleteUserUseCase

    fun provideGetAllUsersUseCase(): GetAllUsersUseCase

    fun provideGetTokenUseCase(): GetTokenUseCase

    fun provideGetUserByIdUseCase(): GetUserByIdUseCase

    fun provideGetUserByNameUseCase(): GetUserByNameUseCase

    fun provideInsertUserUseCase(): InsertUserUseCase

    fun provideSetTokenUseCase(): SetTokenUseCase

    fun provideUpdateUserUseCase(): UpdateUserUseCase

    fun inject(repository: Repository)
}