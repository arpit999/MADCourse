package com.example.madcourse.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.madcourse.data.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao
}