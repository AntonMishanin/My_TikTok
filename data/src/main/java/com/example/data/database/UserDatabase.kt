package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile
        private var instance: UserDatabase? = null

        fun getUserDatabase(context: Context): UserDatabase {
            if (instance != null) {
                return instance as UserDatabase
            }
            synchronized(this) {
                instance = Room
                    .databaseBuilder(context, UserDatabase::class.java, "user.db")
                    .fallbackToDestructiveMigration()
                    .build()

                return instance as UserDatabase
            }
        }
    }
}