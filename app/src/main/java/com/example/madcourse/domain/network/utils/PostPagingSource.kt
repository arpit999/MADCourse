package com.example.madcourse.domain.network.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.madcourse.domain.network.api.PostsApi
import com.example.madcourse.domain.network.api.UserApi
import com.example.madcourse.domain.network.model.Post
import com.example.madcourse.domain.network.model.User

private const val FIRST_PAGE = 1

class PostPagingSource(
    private val postsApi: UserApi
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {

            val page = params.key ?: FIRST_PAGE
            val response = postsApi.getUsers(page = page, size = params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}