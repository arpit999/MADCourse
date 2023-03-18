package com.example.madcourse.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcourse.model.users
import com.example.madcourse.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val dao: UserDao) : ViewModel() {

    init {
        viewModelScope.launch {
            dao.upsertUser(users.subList(1,5))
        }
    }


}