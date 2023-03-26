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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: GithubRepo,
) : ViewModel() {


    private val _userList = MutableStateFlow(emptyList<User>())
    val userList: StateFlow<List<User>> = _userList

    //    var userList: StateFlow<List<User>> = MutableStateFlow(emptyList())
    var userDetails: StateFlow<UserDetails?> = MutableStateFlow(null)

    fun getUsers(user: String, page: Int) {
        viewModelScope.launch {
            _userList.value = repo.getUsers(user, page)
            Log.d("TAG", "getUsers:  ${userList.value}")
        }
    }

    fun getUserDetails(username: String) {
        viewModelScope.launch {
            userDetails = repo.getUserDetails(username).stateIn(viewModelScope)
        }
    }

}