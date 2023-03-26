package com.example.madcourse.domain.network

import androidx.paging.PagingState
import com.example.madcourse.domain.UserViewModel
import com.example.madcourse.domain.network.model.User
import javax.inject.Inject

class PagingSource @Inject constructor(private val viewModel: UserViewModel) :
    androidx.paging.PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1

            val response = viewModel.userList.value
            val nextKey = if (response.isNotEmpty()) viewModel.pageNumber.value + 1 else null

            LoadResult.Page(data = response, prevKey = null, nextKey = nextKey)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}