package com.example.sincity.repository.data

import com.example.sincity.model.User

class RemoteDataSource2 {

    suspend fun getUsersList() : List<User> {

        // throw Exception("Unable to get users")

        return listOf(
            User(0, "Otto", 23, "Jefe"),
            User(1, "Ricardo", 23, "Developer"),
            User(2, "Gaby", 23, "UI/UX"),
            User(3, "Zayda", 23, "PM"),
            User(4, "Oscar", 23, "Developer"),
            User(5, "Enrique", 23, "Developer")
        )
    }

}