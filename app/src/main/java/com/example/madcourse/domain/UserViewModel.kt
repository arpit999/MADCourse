package com.example.madcourse.domain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.madcourse.domain.network.GithubApi
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
    private val api: GithubApi
) : ViewModel() {

    var pageNumber = mutableStateOf(0)
//    private val pageNumber: StateFlow<Int> = _pageNumber

    private var _searchText = MutableStateFlow("arpit")
    val searchText: StateFlow<String> = _searchText

//    val userPager = Pager(PagingConfig(pageSize = 4)) {
//        com.example.madcourse.domain.network.PagingSource(api, searchText.value)
//    }.flow.cachedIn(viewModelScope)

    fun getGitHubUsers(username: String = searchText.value): Flow<PagingData<User>> =
        repo.getGitHubUsers(username = username)
            .cachedIn(viewModelScope)

    private val _userList = MutableStateFlow(emptyList<User>())
    val userList: StateFlow<List<User>> = _userList

    private val _userDetails: MutableStateFlow<UserDetails?> = MutableStateFlow(null)
    val userDetails: StateFlow<UserDetails?> = _userDetails

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun getPageNumber(): Int {
        pageNumber.value += 1
        return pageNumber.value
    }
    //    var userList: StateFlow<List<User>> = MutableStateFlow(emptyList())
//    var userDetails: StateFlow<UserDetails?> = MutableStateFlow(null)

//    fun getUsers(page: Int) {
//        viewModelScope.launch {
//            _userList.value = repo.getUsers(searchText.value, page)
//            Log.d("TAG", "getUsers:  ${userList.value}")
//        }
//    }

    fun getUserDetails(username: String) {
        viewModelScope.launch {
            _userDetails.value = repo.getUserDetails(username)
        }
    }

}