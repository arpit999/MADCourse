package com.example.madcourse.domain.network.api

import com.example.madcourse.domain.network.model.User
import com.example.madcourse.domain.network.model.UserDetail
import com.example.madcourse.domain.network.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.Flow

interface UserApi {

    @GET("users?")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("limit") size: Int = 20
    ): List<User>

    @GET("users/{userId}")
    suspend fun getUserDetail(@Path("userId") userId: String): Response<UserDetail>



}