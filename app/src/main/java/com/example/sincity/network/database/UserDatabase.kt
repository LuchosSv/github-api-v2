package com.example.sincity.network.database

import androidx.room.RoomDatabase
import com.example.sincity.network.Dao.UserDao

abstract class UserDatabase() : RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{

        @Volatile
        private var INSTANCE: UserDatabase? = null

    }

}