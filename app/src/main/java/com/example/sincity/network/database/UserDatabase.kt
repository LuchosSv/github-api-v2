package com.example.sincity.network.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sincity.network.Dao.UserDao
import com.example.sincity.utility.NAME_DATABASE

//@Database(entities = [UserEntity::class, ProfileEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase() : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(UserDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        NAME_DATABASE
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}