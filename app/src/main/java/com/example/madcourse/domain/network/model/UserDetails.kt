package com.example.madcourse.domain.network.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class UserDetails(
    @SerializedName("name")
    val fullName: String?,

    @SerializedName("login")
    val userName: String,

    @SerializedName("avatar_url")
    val profilePic: String,

    @SerializedName("followers")
    val followers: String,

    @SerializedName("public_repos")
    val repositories: String,

    @SerializedName("following")
    val following: String,

    @SerializedName("bio")
    val biography: String?,

    @SerializedName("created_at")
    val createdAt: String,
) {

    fun createAt(): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm:ss a", Locale.getDefault())

        val inputDate = inputDateFormat.parse(createdAt)

        return inputDate?.let { outputDateFormat.format(it) } ?: createdAt
    }


}
