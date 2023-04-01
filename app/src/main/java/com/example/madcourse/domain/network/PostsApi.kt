package com.example.madcourse.domain.network

import com.example.madcourse.domain.network.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    @GET("list?")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("limit") size: Int = 14
    ): List<Post>
}