package com.example.sincity.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.example.sincity.fragment.ProfileFragmentArgs
import com.example.sincity.model.ProfileModel
import com.example.sincity.model.UserModel
import com.example.sincity.network.Entity.UserEntity
import com.example.sincity.network.database.UserDatabase
import com.example.sincity.repository.data.RemoteDataSource
import com.example.sincity.repository.local.LocalDataSource
import com.example.sincity.utility.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
   private val localDataSource: LocalDataSource
) {

    suspend fun getUserByRepository(): List<UserModel> {
        Log.i("Repository", "Success :D")
        return remoteDataSource.getUserListRemote()
    }

    suspend fun getProfileRepository(name: String): ProfileModel {
        return remoteDataSource.getProfileListRemote(name)
    }

    //Local
    fun getLocalUsers() = localDataSource.getLocalUsers()

    /**
     * suspend fun getUsersFromGitHub() {
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
