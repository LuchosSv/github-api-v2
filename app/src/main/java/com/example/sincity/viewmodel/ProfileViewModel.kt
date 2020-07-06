package com.example.sincity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sincity.model.ProfileModel
import com.example.sincity.repository.UserRepository
import com.example.sincity.utility.ERROR
import com.example.sincity.utility.LOADING
import com.example.sincity.utility.RetrofitFactory
import com.example.sincity.utility.SUCCESS
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ProfileViewModel : ViewModel() {

    private val _profileList = MutableLiveData<ProfileModel>()
    val profileList: LiveData<ProfileModel>
        get() = _profileList

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _usersErrorMessage = MutableLiveData<String>()
    val usersErrorMessage: LiveData<String>
        get() = _usersErrorMessage

    private val repository = UserRepository()

    fun getProfileData(name: String){
        viewModelScope.launch {
            _status.value = LOADING
            try {
                _profileList.value = repository.getProfileRepository(name)
                _status.value = SUCCESS
            } catch (e: Exception) {
                Log.e("viewModel, Profile", "Error, ${e.message}")
                _status.value = ERROR
                _usersErrorMessage.value = e.message
            }
        }
    }

    /*fun getProfileData(name: String) {

        _status.value = LOADING

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
            _status.value = SUCCESS
        } catch (e: Exception) {
            _status.value = ERROR
            _usersErrorMessage.value = e.message
        }
    }*/

}