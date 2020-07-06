package com.example.sincity.repository.data

import android.util.Log
import com.example.sincity.model.ProfileModel
import com.example.sincity.utility.RetrofitFactory

class RemoteDataSource() {
    var client = RetrofitFactory.makeRetrofitService()

    suspend fun getUserListRemote() = client.getUser()
    suspend fun getProfileListRemote(name: String) = client.getProfile(name)

    /*suspend fun getUserListRemote(): List<UserModel> {
        Log.i("RemoteDataSource Init", "Success")
        RetrofitFactory.makeRetrofitService().getUser().enqueue(object : Callback<List<UserModel>> {
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.e("RemoteDataSource", "Failure, ${t.message}")
            }

            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                if (response.isSuccessful) {
                    Log.i("RemoteDataSource", "Response, ${response.code()}")
                    //_userRemote.value = response.body()
                    //return response.body()
                    bigList2 = response.body()!!
                }
            }
        })
        return bigList2
    }*/

}