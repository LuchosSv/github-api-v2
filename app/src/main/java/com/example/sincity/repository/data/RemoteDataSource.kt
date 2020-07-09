package com.example.sincity.repository.data

import com.example.sincity.utility.RetrofitFactory

class RemoteDataSource() {
    var client = RetrofitFactory.makeRetrofitService()

    suspend fun getUserListRemote() = client.getUser()
    suspend fun getProfileListRemote(name: String) = client.getProfile(name)

}