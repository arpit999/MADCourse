package com.example.madcourse.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcourse.domain.network.model.User
import com.example.madcourse.domain.network.model.UserDataStore
import com.example.madcourse.domain.network.model.users
import com.example.madcourse.domain.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val dao: UserDao,
    private val dataStore: UserDataStore,
) : ViewModel() {

    val isUserAdded: StateFlow<Boolean> = dataStore.isUserAdded.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    val userList = dao.getAllUser().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    var userDetails: Flow<User> = dao.getUser(1)

    fun upsertUsers() {
        viewModelScope.launch {
            dao.upsertUser(users.asSequence().shuffled().take(5).toList())
            dataStore.storeIsUserAdded(true)
        }
    }

    fun addUser() {
        viewModelScope.launch {
            dao.upsertUser(users.asSequence().shuffled().take(1).toList())
        }
    }

    fun getUser(tableId: Int) {
        viewModelScope.launch {
          userDetails =  dao.getUser(tableId)
        }
    }

}