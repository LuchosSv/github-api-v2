package com.example.sincity.network.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sincity.model.UserModel

@Entity(tableName = "user_table")
data class UserEntity constructor(

    val login: String,
    @PrimaryKey @ColumnInfo(name = "user_id") val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean

)

fun List<UserEntity>.asDomainModel(): List<UserModel> {
    return map {
        UserModel(
            login = it.login,
            id = it.id,
            node_id = it.node_id,
            avatar_url = it.avatar_url,
            gravatar_id = it.gravatar_id,
            url = it.url,
            html_url = it.html_url,
            followers_url = it.followers_url,
            following_url = it.following_url,
            gists_url = it.gists_url,
            starred_url = it.starred_url,
            subscriptions_url = it.subscriptions_url,
            organizations_url = it.organizations_url,
            repos_url = it.repos_url,
            events_url = it.events_url,
            received_events_url = it.received_events_url,
            type = it.type,
            site_admin = it.site_admin
        )
    }
}