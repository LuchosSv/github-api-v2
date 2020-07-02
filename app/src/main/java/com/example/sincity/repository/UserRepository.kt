package com.example.sincity.repository

import androidx.lifecycle.LiveData
import com.example.sincity.network.Entity.UserEntity
import com.example.sincity.network.database.UserDatabase
import com.example.sincity.utility.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class UserRepository(private val database: UserDatabase) {

    val ace: LiveData<List<UserEntity>> = database.userDao().getUser()

    //retrfofit es una lista pero insertUser es un objeto.. conflicto
    /*suspend fun refreshAce(){
        withContext(Dispatchers.IO){
            val playlist = RetrofitFactory.makeRetrofitService().getUser().await()
            database.userDao().insertUser(playlist)

        }
    }*/
}
