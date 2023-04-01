package com.example.madcourse.domain.network.api

import com.example.madcourse.domain.network.model.Profile
import retrofit2.http.GET

interface ProfileDetailsApi {
    @GET("api/v2/users?size=1")
    suspend fun getProfile(): Profile
}