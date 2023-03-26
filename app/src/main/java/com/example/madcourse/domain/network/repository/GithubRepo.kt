package com.example.madcourse.domain.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.madcourse.domain.network.GithubApi
import com.example.madcourse.domain.network.PagingSource
import com.example.madcourse.domain.network.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepo @Inject constructor(private val api: GithubApi) {

    suspend fun getUsers(search: String, page: Int) = api.getUserList(search, page)

    fun getGitHubUsers(username: String): Flow<PagingData<User>> =
        Pager(config = PagingConfig(pageSize = 5)) {
            PagingSource(api, username)
        }.flow


    suspend fun getUserDetails(username: String) = api.getUserDetails(username)

}