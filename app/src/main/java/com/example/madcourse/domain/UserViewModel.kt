package com.example.madcourse.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.madcourse.domain.network.api.PostsApi
import com.example.madcourse.domain.network.api.ProfileDetailsApi
import com.example.madcourse.domain.network.api.UserApi
import com.example.madcourse.domain.network.model.Post
import com.example.madcourse.domain.network.model.Profile
import com.example.madcourse.domain.network.model.User
import com.example.madcourse.domain.network.model.UserDetail
import com.example.madcourse.domain.network.utils.NetworkResult
import com.example.madcourse.domain.network.utils.PostPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val profileDetailsApi: ProfileDetailsApi,
    private val postsApi: PostsApi,
    private val userApi: UserApi
) : ViewModel() {

    init {
        getProfileDetails()
    }

    private val _profileDetails: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val profileDetails: StateFlow<Profile?> = _profileDetails

    private var _userDetail: MutableStateFlow<NetworkResult<UserDetail>> = MutableStateFlow(NetworkResult.Idle)
    val userDetail: StateFlow<NetworkResult<UserDetail>> = _userDetail

    private fun getProfileDetails() {
        viewModelScope.launch {
            val result = profileDetailsApi.getProfile()
            _profileDetails.emit(result)
        }
    }

    private suspend fun getUserDetail(userId: String) {
        _userDetail = flow {
            try {
                val response = userApi.getUserDetail(userId)
                when {
                    response.isSuccessful -> {
                        response.body()?.let { emit(NetworkResult.Success(it)) }
                    }
                    else -> {
                        emit(NetworkResult.Error(response.errorBody().toString()))
                    }
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.localizedMessage ?: "An unknown error occurred"))
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            NetworkResult.Loading
        ) as MutableStateFlow<NetworkResult<UserDetail>>
    }

    //PAGING
    fun getPaginatedPosts(): Flow<PagingData<User>> =
        Pager(config = PagingConfig(pageSize = 5)) {
            PostPagingSource(userApi)
        }.flow.cachedIn(viewModelScope)

}