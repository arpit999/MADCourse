package com.example.madcourse.domain.network.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    val postList: List<Post>
)

data class Post(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("width") val width: String,
    @SerializedName("height") val height: String,
    @SerializedName("url") val url: String,
    @SerializedName("download_url") val downloadUrl: String
)
