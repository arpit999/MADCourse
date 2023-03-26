package com.example.madcourse.domain.network

import androidx.paging.PagingState
import com.example.madcourse.domain.network.model.User

const val FIRST_PAGE = 1

class PagingSource(private val api: GithubApi, private val username: String) :
    androidx.paging.PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: FIRST_PAGE

            val response = api.getUserList(username, page, params.loadSize).users
            val nextKey = if (response.isNotEmpty()) page + 1 else null
            val prevKey = if (page == 1) null else page

            LoadResult.Page(data = response, prevKey = prevKey, nextKey = nextKey)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}