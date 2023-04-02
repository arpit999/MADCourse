package com.example.madcourse.domain.network.utils

object Constant {
    const val retrofit_profile: String = "RETROFIT_PROFILE_DETAILS"
    const val retrofit_post: String = "RETROFIT_POST"
    fun generateImageUrl(id: Int, width: Int, height: Int): String {
        return "https://picsum.photos/id/$id/$width/$height"
    }

    const val id = "id"
}