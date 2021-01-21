package com.example.data

import com.example.data.database.UserDao
import com.example.data.preferences.SharedPref
import com.example.domain.entity.UserEntity
import com.example.domain.repository.Repository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

class RepositoryImpl(
    private val sharedPref: SharedPref,
    private val userDao: UserDao
): Repository{

    /*
    Splash
     */
   override fun getToken(): Long = sharedPref.getToken()

    /*
    Authorization
     */

    override fun setToken(token: Long) = sharedPref.setToken(token)

    /*
    Database
     */

    override fun insertUser(user: UserEntity): Single<Long> =
        Single.fromCallable { userDao.insertUser(user) }

    override fun updateUser(user: UserEntity): Completable =
        Completable.fromAction { userDao.updateUser(user) }

    override fun deleteUser(user: UserEntity) {
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

    override fun getAllUsers() {
        val subscribe = userDao.getAllUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                // Log.d("TAG", "list size = ${it.size}")
            }
    }

    override fun getUserById(id: Int): Flowable<UserEntity> = userDao.getUserById(id)

    override fun getUserByName(userName: String): Flowable<UserEntity> = userDao.getUserByName(userName)
}