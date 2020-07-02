package com.example.sincity.network.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sincity.model.ProfileModel
import com.example.sincity.model.UserModel
import com.example.sincity.network.Entity.ProfileEntity
import com.example.sincity.network.Entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user_table WHERE user_id = :userId")
    fun load(userId: Int): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg users: UserEntity)

    //fun insertUser(vararg users: ProfileEntity)
}