package com.example.sincity.repository

import com.example.sincity.model.ProfileModel
import com.example.sincity.model.UserModel
import com.example.sincity.network.Entity.ProfileEntity
import com.example.sincity.network.Entity.UserEntity
import com.example.sincity.repository.data.RemoteDataSource
import com.example.sincity.repository.local.LocalDataSource

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
   private val localDataSource: LocalDataSource
) {

    //remote
    suspend fun getUserByRepository(): List<UserModel> {
        return remoteDataSource.getUserListRemote()
    }

    suspend fun getProfileRepository(name: String): ProfileModel {
        return remoteDataSource.getProfileListRemote(name)
    }

    //Local list profile
    fun getLocalProfile() = localDataSource.getListProfile()

    //favorite insert profile
    suspend fun saveLocalProfile(profileEntity: ProfileEntity){
        localDataSource.saveLocalProfile(profileEntity)
    }

    /*suspend fun deleteLocalUser(userEntity: UserEntity){
        localDataSource.deleteLocalUser(userEntity)
    }*/

    /**
    suspend fun getUsersFromGitHub() {
    try {
    // Esto retornaria una lista de GithubUsers
        val response = remoteDataSource.getGitHubUsers()

    // Esto guardria los usuarios desde github
        localDataSource.insertGitHubUsers(response)
    }catch (e: Exception){
        Log.e("", "", e)
        }
    }
     */

}
