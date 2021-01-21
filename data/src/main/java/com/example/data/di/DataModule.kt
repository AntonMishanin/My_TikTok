package com.example.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.data.RepositoryImpl
import com.example.data.database.UserDao
import com.example.data.database.UserDatabase
import com.example.data.preferences.SharedPref
import com.example.domain.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

   @Provides
   fun provideRepository(sharedPref: SharedPref, userDao: UserDao): Repository =
       RepositoryImpl(sharedPref, userDao)

   @Provides
   fun provideSharedPref(sharedPreferences: SharedPreferences): SharedPref =
       SharedPref(sharedPreferences)

   @Provides
   fun provideSharedPreferences(application: Application): SharedPreferences =
       application.applicationContext.getSharedPreferences("Shared_preferences_token", Context.MODE_PRIVATE)

   @Provides
   fun provideUserDao(userDatabase: UserDatabase): UserDao = userDatabase.getUserDao()

   @Provides
   fun provideUserDatabase(application: Application): UserDatabase = UserDatabase.getUserDatabase(application.applicationContext)
}