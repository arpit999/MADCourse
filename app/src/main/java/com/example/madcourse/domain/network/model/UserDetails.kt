package com.example.madcourse.domain.network.model

import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("name")
    val fullName: String,

    @SerializedName("login")
    val userName: String,

    @SerializedName("followers")
    val followers: String,

    @SerializedName("public_repos")
    val repositories: String,

    @SerializedName("following")
    val following: String,

    @SerializedName("bio")
    val biography: String,

    @SerializedName("created_at")
    val createdAt: String,
)
