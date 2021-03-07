package com.example.shared_domain.repository

import com.example.shared_domain.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


interface Repository {
    /*
   Splash
    */
    fun getToken(): Long

    /*
    Authorization
     */

    fun setToken(token: Long)

    /*
    Database
     */

    fun insertUser(user: UserEntity): Single<Long>

    fun updateUser(user: UserEntity): Completable

    fun deleteUser(user: UserEntity)

    fun getAllUsers()

    fun getUserById(id: Int): Flowable<UserEntity>

    fun getUserByName(userName: String): Flowable<UserEntity>
}