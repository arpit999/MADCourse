package com.example.madcourse.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcourse.data.UserDataStore
import com.example.madcourse.data.users
import com.example.madcourse.domain.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val dao: UserDao, private val dataStore: UserDataStore) : ViewModel() {

//    private var _isUserAdded = dataStore.isUserAdded
    val isUserAdded get() = dataStore.isUserAdded

    init {
//        _isUserAdded = dataStore.isUserAdded.stateIn(viewModelScope, SharingStarted.Eagerly, false)
    }

    fun upsertUsers() {
        viewModelScope.launch {
            dao.upsertUser(users.asSequence().shuffled().take(5).toList())
            dataStore.storeIsUserAdded(true)
        }
    }
}