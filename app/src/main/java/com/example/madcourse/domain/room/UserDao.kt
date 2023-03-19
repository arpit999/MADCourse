package com.example.madcourse.domain.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.madcourse.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: List<User>)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE tableId=:tableId")
    fun getUser(tableId: Int): Flow<User>

}