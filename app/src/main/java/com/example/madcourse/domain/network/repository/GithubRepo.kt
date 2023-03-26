package com.example.madcourse.domain.network.repository

import com.example.madcourse.domain.network.GithubApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubRepo @Inject constructor(private val api: GithubApi) {

    suspend fun getUsers(search: String, page: Int) = api.getUserList(search, page).users

    suspend fun getUserDetails(username: String) = flow {
        val result = api.getUserDetails(username)
        emit(result)
    }

}