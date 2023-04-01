package com.example.madcourse.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcourse.domain.network.GithubApi
import com.example.madcourse.domain.network.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val api: GithubApi,
) : ViewModel() {

    init {
        getProfile()
    }

    private var _searchText = MutableStateFlow("arpit")
    val searchText: StateFlow<String> = _searchText

//    fun getGitHubUsers(username: String = searchText.value): Flow<PagingData<User>> =
//        repo.getPosts(username = username)
//            .cachedIn(viewModelScope)

    private val _userProfile: MutableStateFlow<Profile?> = MutableStateFlow(null)
    val userProfile: StateFlow<Profile?> = _userProfile

//    fun getPosts(): Flow<PagingData<User>> =
//        Pager(config = PagingConfig(pageSize = 14)) {
//            PostPagingSource(api)
//        }.flow

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    private fun getProfile() {
        viewModelScope.launch {
            val result = api.getProfile()
            _userProfile.emit(result)
//            val result = api.getProfile().body()
//            NetworkResult.Success(result).let { _userDetails.emit(it) }
        }
    }

}