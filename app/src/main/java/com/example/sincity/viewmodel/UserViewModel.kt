package com.example.sincity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sincity.model.UserModel
import com.example.sincity.repository.UserRepository
import com.example.sincity.repository.data.RemoteDataSource
import com.example.sincity.utility.ERROR
import com.example.sincity.utility.LOADING
import com.example.sincity.utility.RetrofitFactory
import com.example.sincity.utility.SUCCESS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UserViewModel() : ViewModel() {

    /**
     * Live data de la lista del objeto
     */
    private val _userList = MutableLiveData<List<UserModel>>()
    val userList: LiveData<List<UserModel>>
        get() = _userList

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

    //private val repository = UserRepository(RemoteDataSource())
    private val repository = UserRepository()

    init {

        //getUserModelData()
        load()

    }

    /**
     * Metodo para pedir la lista al repository
     */
    private fun load() {
        viewModelScope.launch {
            _status.value = LOADING
            try {
                _userList.value = repository.getUserByRepository()
                Log.i("viewModel", "Success, ${_userList.value!!.size}")
                _status.value = SUCCESS
            } catch (e: Exception) {
                _status.value = ERROR
                _usersErrorMessage.value = e.message
                Log.e("viewModel", "Error, ${e.message}")
            }
        }
    }
    /*private fun getUserModelData() {

        _status.value = LOADING

        /**
         * Metodo handler crea un delay en la llamada a la api de 3 segundos
         * Handler().postDelayed({}, 3000)
         */

        /**
         * Hacermos la llamada a la api
         * El try nos ayuda a manejar errores en la llamada
         */
        try {
            //throw Exception("No pudimos cargar los usuarios")
            RetrofitFactory.makeRetrofitService().getUser()
                .enqueue(object : Callback<List<UserModel>> {
                    override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                        Log.e("Error en viewModel", "Cause: ${t.message}")
                    }

                    override fun onResponse(
                        call: Call<List<UserModel>>,
                        response: Response<List<UserModel>>
                    ) {
                       if (response.isSuccessful){
                           _userList.value = response.body()!!
                           Log.i("OK", "Success, ${response.code()}")
                       }else{
                           //Error code 403 detector
                           Log.i("Bad", "Error, ${response.code()}, ${response.message()}")
                       }
                    }
                })
            _status.value = SUCCESS
        } catch (e: Exception) {
            _status.value = ERROR
            _usersErrorMessage.value = e.message
        }
    }*/

}