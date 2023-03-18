package com.example.madcourse.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.madcourse.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao
}