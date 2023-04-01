package com.example.madcourse.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcourse.domain.network.PostsApi
import com.example.madcourse.domain.network.ProfileDetailsApi
import com.example.madcourse.domain.network.model.Post
import com.example.madcourse.domain.network.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
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
        getPosts()
    }

    private val _profileDetails: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val profileDetails: StateFlow<Profile?> = _profileDetails

    private val _posts = mutableStateListOf<Post>()
    val posts get() = _posts

    private fun getProfileDetails() {
        viewModelScope.launch {
            val result = profileDetailsApi.getProfile()
            _profileDetails.emit(result)
//            val result = api.getProfile().body()
//            NetworkResult.Success(result).let { _userDetails.emit(it) }
        }
    }

    private fun getPosts() {
        viewModelScope.launch {
            val result = postsApi.getPosts(1, 34)
            _posts.addAll(result)
        }
    }


}