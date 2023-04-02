package com.example.madcourse.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.madcourse.domain.network.api.PostsApi
import com.example.madcourse.domain.network.api.ProfileDetailsApi
import com.example.madcourse.domain.network.model.Post
import com.example.madcourse.domain.network.model.Profile
import com.example.madcourse.domain.network.utils.PostPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val profileDetailsApi: ProfileDetailsApi,
    private val postsApi: PostsApi
) : ViewModel() {

    init {
        getProfileDetails()
    }

    private val _profileDetails: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val profileDetails: StateFlow<Profile?> = _profileDetails

    private fun getProfileDetails() {
        viewModelScope.launch {
            val result = profileDetailsApi.getProfile()
            _profileDetails.emit(result)
        }
    }

    //PAGING
    fun getPaginatedPosts(): Flow<PagingData<Post>> =
        Pager(config = PagingConfig(pageSize = 5)) {
            PostPagingSource(postsApi)
        }.flow.cachedIn(viewModelScope)

}