package com.example.madcourse.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.madcourse.domain.network.model.User
import com.example.madcourse.domain.network.model.UserDetails
import com.example.madcourse.domain.network.repository.GithubRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: GithubRepo,
) : ViewModel() {

    private var _searchText = MutableStateFlow("arpit")
    val searchText: StateFlow<String> = _searchText

    fun getGitHubUsers(username: String = searchText.value): Flow<PagingData<User>> =
        repo.getGitHubUsers(username = username)
            .cachedIn(viewModelScope)

    private val _userDetails: MutableStateFlow<UserDetails?> = MutableStateFlow(null)
    val userDetails: StateFlow<UserDetails?> = _userDetails

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun getUserDetails(username: String) {
        viewModelScope.launch {
            _userDetails.value = repo.getUserDetails(username)
        }
    }

}