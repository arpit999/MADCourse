package com.example.madcourse.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcourse.domain.network.model.User
import com.example.madcourse.domain.network.model.UserDetails
import com.example.madcourse.domain.network.repository.GithubRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: GithubRepo,
) : ViewModel() {

    var pageNumber = MutableStateFlow(0)
//    private val pageNumber: StateFlow<Int> = _pageNumber

    private var _searchText = MutableStateFlow("")
    private val searchText: StateFlow<String> = _searchText

    private val _userList = MutableStateFlow(emptyList<User>())
    val userList: StateFlow<List<User>> = _userList

    private val _userDetails: MutableStateFlow<UserDetails?> = MutableStateFlow(null)
    val userDetails: StateFlow<UserDetails?> = _userDetails

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }
    //    var userList: StateFlow<List<User>> = MutableStateFlow(emptyList())
//    var userDetails: StateFlow<UserDetails?> = MutableStateFlow(null)

    fun getUsers() {
        pageNumber.value += 1
        viewModelScope.launch {
            _userList.value = repo.getUsers(searchText.value, pageNumber.value)
            Log.d("TAG", "getUsers:  ${userList.value}")
        }
    }

    fun getUserDetails(username: String) {
        viewModelScope.launch {
            _userDetails.value = repo.getUserDetails(username)
        }
    }

}