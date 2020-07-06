package com.example.sincity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sincity.model.User
import com.example.sincity.repository.UserRepository2
import com.example.sincity.repository.data.RemoteDataSource
import com.example.sincity.repository.data.RemoteDataSource2
import com.example.sincity.utility.ERROR
import com.example.sincity.utility.LOADING
import com.example.sincity.utility.SUCCESS
import kotlinx.coroutines.launch

class FavoriteViewModel() : ViewModel() {

    private val repository = UserRepository2(RemoteDataSource2())

    private val _usersList = MutableLiveData<List<User>>()
    val usersList: LiveData<List<User>>
        get() = _usersList

    private val _apiStatus = MutableLiveData<String>()
    val apiStatus: LiveData<String>
        get() = _apiStatus

    private val _usersListErrorMessage = MutableLiveData<String>()
    val usersListErrorMessage: LiveData<String>
        get() = _usersListErrorMessage

    private val _messageFromRepository = MutableLiveData<String>()
    val messageFromRepository: LiveData<String>
        get() = _messageFromRepository

    init {
        loadUsers()

    }

    private fun loadUsers() {

        viewModelScope.launch {

            _apiStatus.value = LOADING

            try {

                _usersList.value = repository.getUsersList()

                _apiStatus.value = SUCCESS

            } catch (e: Exception) {
                _apiStatus.value = ERROR
                _usersListErrorMessage.value = e.message
            }
        }
    }

}