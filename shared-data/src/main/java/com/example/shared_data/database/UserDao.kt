package com.example.shared_data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.shared_domain.entity.UserEntity
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insertUser(user: UserEntity): Long

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM User")
    fun getAllUsers(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserById(id: Int): Flowable<UserEntity>

    @Query("SELECT * FROM User WHERE name = :userName")
    fun getUserByName(userName: String): Flowable<UserEntity>
}