package com.example.sincity.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.sincity.model.UserModel
import com.example.sincity.network.database.UserDatabase
import com.example.sincity.repository.UserRepository
import com.example.sincity.repository.data.RemoteDataSource
import com.example.sincity.repository.local.LocalDataSource
import com.example.sincity.utility.ERROR
import com.example.sincity.utility.LOADING
import com.example.sincity.utility.SUCCESS
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel(private val applicationContext: Context) : ViewModel() {

    /**
     * Live data para manejar el status (pantalla de carga)
     */
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    /**
     * Live data para la pantalla de carga (mensaje de error)
     */
    private val _usersErrorMessage = MutableLiveData<String>()
    val usersErrorMessage: LiveData<String>
        get() = _usersErrorMessage

    /**
     * Live data de la lista del objeto
     */
    private val _userList = MutableLiveData<List<UserModel>>()
    val userList: LiveData<List<UserModel>>
        get() = _userList

    //Repository, Dao
    private val dao = UserDatabase.getInstance(applicationContext).userDao()
    private val repository = UserRepository(RemoteDataSource(), LocalDataSource(dao))
    //private val repository = UserRepository(RemoteDataSource())

    init {
        getListUserVM()
    }

    /**
     * Metodo para pedir la lista al repository
     */
    private fun getListUserVM() {
        viewModelScope.launch {
            _status.value = LOADING
            try {
                _userList.value = repository.getUserByRepository()
                Log.i("viewModel", "Success")
                _status.value = SUCCESS
            } catch (e: Exception) {
                _status.value = ERROR
                _usersErrorMessage.value = e.message
                Log.e("viewModel", "Error, ${e.message}")
            }
        }
    }

    class UserViewModelFactory(private val app: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(app) as T
            }
            throw IllegalArgumentException("Invalid Viewmodel")
        }
    }

}