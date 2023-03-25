package com.example.madcourse.domain.network.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("login")
    val username: String,

    @SerializedName("avatar_url")
    val profilePicture: String,

    @SerializedName("html_url")
    val profileURL: String
)
