package com.example.sincity.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.sincity.model.ProfileModel
import com.example.sincity.network.Entity.ProfileEntity
import com.example.sincity.network.database.UserDatabase
import com.example.sincity.repository.UserRepository
import com.example.sincity.repository.data.RemoteDataSource
import com.example.sincity.repository.local.LocalDataSource
import kotlinx.coroutines.launch

class FavoriteViewModel (private val applicationContext: Context): ViewModel(){

    //Repository, Dao
    private val dao = UserDatabase.getInstance(applicationContext).userDao()
    private val repository = UserRepository(RemoteDataSource(), LocalDataSource(dao))

    private val _favoriteList = MutableLiveData<ProfileModel>()
    val favoriteList: LiveData<ProfileModel>
        get() = _favoriteList

    val localUsersList: LiveData<List<ProfileEntity>> = repository.getLocalProfile()

    fun deleteLocalUser(profileEntity: ProfileEntity){
        viewModelScope.launch {
            repository.deleteLocalProfile(profileEntity)
        }
    }

    class ProfileViewModelFactory(private val app: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
                return FavoriteViewModel(app) as T
            }
            throw IllegalArgumentException("Invalid Viewmodel")
        }
    }

}