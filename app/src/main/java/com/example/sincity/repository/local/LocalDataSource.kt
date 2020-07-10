package com.example.sincity.repository.local

import com.example.sincity.network.Dao.UserDao
import com.example.sincity.network.Entity.ProfileEntity
import com.example.sincity.network.Entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val dao: UserDao) {

    //list
    fun getListProfile() = dao.getListProfile()

    //insert
    suspend fun saveLocalProfile(profileEntity: ProfileEntity) = withContext(Dispatchers.IO){
        dao.profileUser(profileEntity)
    }

    //delete
    suspend fun deleteLocalUser(profileEntity: ProfileEntity) = withContext(Dispatchers.IO) {
        dao.deleteUser(profileEntity)
    }


}