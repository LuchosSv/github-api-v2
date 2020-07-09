package com.example.sincity.repository.local

import com.example.sincity.network.Dao.UserDao
import com.example.sincity.network.Entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val dao: UserDao) {

    fun getLocalUsers() = dao.getListUser()

    suspend fun saveLocalUser(userEntity: UserEntity) = withContext(Dispatchers.IO) {
        dao.insertUser(userEntity)
    }

}