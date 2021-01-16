package com.example.data

import com.example.data.database.UserDao
import com.example.data.preferences.SharedPref
import com.example.domain.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

class RepositoryImpl(
    private val sharedPref: SharedPref,
    private val userDao: UserDao
) {

    /*
    Splash
     */
    fun getToken(): Long = sharedPref.getToken()

    /*
    Authorization
     */

    fun setToken(token: Long) = sharedPref.setToken(token)

    /*
    Database
     */

    fun insertUser(user: UserEntity): Single<Long> =
        Single.fromCallable { userDao.insertUser(user) }

    fun updateUser(user: UserEntity): Completable =
        Completable.fromAction { userDao.updateUser(user) }

    fun deleteUser(user: UserEntity) {
        val f = Completable.fromAction {
            userDao.deleteUser(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                (object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        // Log.d("TAG", "insert onComplete()")
                    }

                    override fun onError(e: Throwable) {
                        //Log.d("TAG", "insert onError ${e.message}")
                    }
                })
            }
    }

    fun getAllUsers() {
        val subscribe = userDao.getAllUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                // Log.d("TAG", "list size = ${it.size}")
            }
    }

    fun getUserById(id: Int): Flowable<UserEntity> = userDao.getUserById(id)

    fun getUserByName(userName: String): Flowable<UserEntity> = userDao.getUserByName(userName)
}