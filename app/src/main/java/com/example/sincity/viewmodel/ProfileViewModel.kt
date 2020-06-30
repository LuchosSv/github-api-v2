package com.example.sincity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sincity.model.ProfileModel
import com.example.sincity.utility.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private val _profileList = MutableLiveData<ProfileModel>()
    val profileList: LiveData<ProfileModel>
        get() = _profileList

    fun getProfileData(name: String) {

        try {
            RetrofitFactory.makeRetrofitService().getProfile(name)
                .enqueue(object : Callback<ProfileModel> {
                    override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                        Log.e("Error", "ProfileViewModel: $t")
                    }

                    override fun onResponse(
                        call: Call<ProfileModel>,
                        response: Response<ProfileModel>
                    ) {
                        _profileList.value = response.body()!!
                    }
                })
        } catch (e: Exception) {

        }

    }

}