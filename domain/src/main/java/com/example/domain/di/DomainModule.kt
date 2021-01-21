package com.example.domain.di

import com.example.domain.repository.Repository
import com.example.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule(private val repository: Repository) {

    @Provides
    fun provideDeleteUserUseCase(): DeleteUserUseCase =
        DeleteUserUseCase(repository)

    @Provides
    fun provideGetAllUsersUseCase(): GetAllUsersUseCase =
        GetAllUsersUseCase(repository)

    @Provides
    fun provideGetTokenUseCase(): GetTokenUseCase =
        GetTokenUseCase(repository)

    @Provides
    fun provideGetUserByIdUseCase(): GetUserByIdUseCase =
        GetUserByIdUseCase(repository)

    @Provides
    fun provideGetUserByNameUseCase(): GetUserByNameUseCase =
        GetUserByNameUseCase(repository)

    @Provides
    fun provideInsertUserUseCase(): InsertUserUseCase =
        InsertUserUseCase(repository)

    @Provides
    fun provideSetTokenUseCase(): SetTokenUseCase =
        SetTokenUseCase(repository)

    @Provides
    fun provideUpdateUserUseCase(): UpdateUserUseCase =
        UpdateUserUseCase(repository)
}