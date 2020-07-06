package com.example.sincity.interfaces

import com.example.sincity.model.ProfileModel
import com.example.sincity.model.UserModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    //fun getUser(): Call<List<UserModel>>
    suspend fun getUser(): List<UserModel>

    @GET("users/{login}")
    //fun getProfile(@Path("login") login: String): Call<ProfileModel>
    suspend fun getProfile(@Path("login") login: String): ProfileModel

}