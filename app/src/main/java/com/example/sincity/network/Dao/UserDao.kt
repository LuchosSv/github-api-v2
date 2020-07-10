package com.example.sincity.network.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sincity.network.Entity.ProfileEntity
import com.example.sincity.network.Entity.UserEntity

@Dao
interface UserDao {

    //remote

    @Query("SELECT * FROM user_table")
    fun getListUser(): LiveData<List<UserEntity>>

    //@Query("SELECT * FROM user_table WHERE user_id = :userId")//LIMIT 1
    //fun getListUserById(userId: Int): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity) //vararg

    //local

    @Query("SELECT * FROM profile_table")
    fun getListProfile(): LiveData<List<ProfileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun profileUser(profileEntity: ProfileEntity)

    @Delete
    suspend fun deleteUser(profileEntity: ProfileEntity)
}