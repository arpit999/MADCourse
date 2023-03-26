package com.example.madcourse.domain

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

    var userList: StateFlow<List<User>> = MutableStateFlow(emptyList())
    var userDetails: StateFlow<UserDetails?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            userList = repo.getUsers("rajesh", 1).stateIn(viewModelScope)
        }
    }

    fun getUsers(user: String, page: Int) {
        viewModelScope.launch {
            userList = repo.getUsers(user, page).stateIn(viewModelScope)
        }
    }

    fun getUserDetails(username: String) {
        viewModelScope.launch {
            userDetails = repo.getUserDetails(username).stateIn(viewModelScope)
        }
    }

}