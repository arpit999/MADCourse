package com.example.madcourse.domain.network.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    val createdAt: String,

    val name: String,

    @SerializedName("avatar")
    val profilePicture: String,

    val about: String,

    val followers: String,

    val following: String,

    val posts: String,

    val id: String,

    )
