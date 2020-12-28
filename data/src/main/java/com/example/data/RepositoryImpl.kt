package com.example.data

import androidx.room.*
import com.example.data.database.UserDao
import com.example.data.preferences.SharedPref
import com.example.domain.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

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

    fun insertUser(user: UserEntity, onComplete: (Long) -> Unit) {
        val fd = Single.fromCallable { userDao.insertUser(user) }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Long>() {
                override fun onSuccess(t: Long) {
                    onComplete(t)
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    fun updateUser(user: UserEntity) {
        Completable.fromAction {
            userDao.updateUser(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    // Log.d("TAG", "insert onComplete()")
                }

                override fun onError(e: Throwable) {
                    //Log.d("TAG", "insert onError ${e.message}")
                }
            })
    }

    fun deleteUser(user: UserEntity) {
        Completable.fromAction {
            userDao.deleteUser(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    // Log.d("TAG", "insert onComplete()")
                }

                override fun onError(e: Throwable) {
                    //Log.d("TAG", "insert onError ${e.message}")
                }
            })
    }

    fun getAllUsers() {
        val subscribe = userDao.getAllUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                // Log.d("TAG", "list size = ${it.size}")
            }
    }

    fun getUserById(id: Int, onComplete: (UserEntity) -> Unit) {
        val subscribe = userDao.getUserById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                onComplete(it)
            }
    }

    fun getUserByName(userName: String, onComplete: (UserEntity) -> Unit) {
        val subscribe = userDao.getUserByName(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                onComplete(it)
            }
    }
}