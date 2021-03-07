package com.example.shared_data

import com.example.shared_data.database.UserDao
import com.example.shared_data.preferences.SharedPref
import com.example.shared_domain.entity.UserEntity
import com.example.shared_domain.repository.Repository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
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

    //TODO 07.03.2021: finish it
    override fun deleteUser(user: UserEntity) {
        /*
        val f = Completable.fromAction {
            userDao.deleteUser(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                (object : DisposableCompletableObserver() {
                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                    }
                })
            }
         */
    }

    //TODO 07.03.2021: finish it
    override fun getAllUsers() {
        //val subscribe = userDao.getAllUsers()
        //    .observeOn(AndroidSchedulers.mainThread())
        //    .subscribe {
        //    }
    }

    override fun getUserById(id: Int): Flowable<UserEntity> = userDao.getUserById(id)

    override fun getUserByName(userName: String): Flowable<UserEntity> = userDao.getUserByName(userName)
}