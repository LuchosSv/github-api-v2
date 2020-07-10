package com.example.sincity.network.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity constructor(

    val login: String,
    @PrimaryKey @ColumnInfo(name = "profile_id") val id: Int,
    val avatar_url: String

)