package com.example.madcourse.domain.network

import com.example.madcourse.domain.network.model.PostResponse
import com.example.madcourse.domain.network.model.Profile
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("api/v2/users?size=1")
    suspend fun getProfile(): Profile

    @GET("https://picsum.photos/v2/list?")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("limit") size: Int = 14
    ): PostResponse

}