package com.example.madcourse.domain.network.utils

object Constant {
    const val retrofit_profile: String = "RETROFIT_PROFILE_DETAILS"
    const val retrofit_post: String = "RETROFIT_POST"
    const val USER_API: String = "USER_API"
    const val BASE_URL: String = "https://64302d24b289b1dec4c30671.mockapi.io/api/v1/"
    const val id = "id"
    fun generateImageUrl(id: Int, width: Int, height: Int): String {
        return "https://picsum.photos/id/$id/$width/$height"
    }


}