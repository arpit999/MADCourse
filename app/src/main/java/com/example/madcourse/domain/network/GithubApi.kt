package com.example.madcourse.domain.network

import com.example.madcourse.domain.network.model.UserDetails
import com.example.madcourse.domain.network.model.UserList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users?")
    suspend fun getUserList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") size: Int = 4
    ): UserList

    @GET("/users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): UserDetails
}