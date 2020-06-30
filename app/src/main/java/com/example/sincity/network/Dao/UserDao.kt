package com.example.sincity.network.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.sincity.network.Entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user_table WHERE user_id = :userId")
    fun load(userId: Int): LiveData<UserEntity>

}