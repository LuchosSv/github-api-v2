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
    fun getListUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user_table WHERE user_id = :userId")//LIMIT 1
    fun getListUserById(userId: Int): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)
    //fun insertUser(vararg users: UserEntity)

    //Delete
    //fun insertUser(vararg users: ProfileEntity)
}