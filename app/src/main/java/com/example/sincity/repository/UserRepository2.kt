package com.example.sincity.repository

import com.example.sincity.model.User
import com.example.sincity.repository.data.RemoteDataSource2

class UserRepository2 (private val remoteDataSource: RemoteDataSource2){

    suspend fun getUsersList() : List<User> {
        return remoteDataSource.getUsersList()
    }

}