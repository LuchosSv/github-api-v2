package com.example.sincity.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.sincity.model.ProfileModel
import com.example.sincity.network.Entity.ProfileEntity
import com.example.sincity.network.Entity.UserEntity
import com.example.sincity.network.database.UserDatabase
import com.example.sincity.repository.UserRepository
import com.example.sincity.repository.data.RemoteDataSource
import com.example.sincity.repository.local.LocalDataSource
import com.example.sincity.utility.ERROR
import com.example.sincity.utility.LOADING
import com.example.sincity.utility.RetrofitFactory
import com.example.sincity.utility.SUCCESS
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ProfileViewModel(private val applicationContext: Context) : ViewModel() {

    private val _profileList = MutableLiveData<ProfileModel>()
    val profileList: LiveData<ProfileModel>
        get() = _profileList

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _usersErrorMessage = MutableLiveData<String>()
    val usersErrorMessage: LiveData<String>
        get() = _usersErrorMessage

    private val dao = UserDatabase.getInstance(applicationContext).userDao()
    private val repository = UserRepository(RemoteDataSource(), LocalDataSource((dao)))

    //local list profile
    val localProfileList: LiveData<List<ProfileEntity>> = repository.getLocalProfile()

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

    /**
     * Metodo para insertar al repository 2
     */
    fun saveLocalProfile(profileEntity: ProfileEntity){
        viewModelScope.launch {
            repository.saveLocalProfile(profileEntity)
        }
    }

    class ProfileViewModelFactory(private val app: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                return ProfileViewModel(app) as T
            }
            throw IllegalArgumentException("Invalid Viewmodel")
        }
    }

}