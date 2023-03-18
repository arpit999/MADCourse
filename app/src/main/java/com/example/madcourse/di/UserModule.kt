package com.example.madcourse.di

import android.content.Context
import androidx.room.Room
import com.example.madcourse.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext appContext: Context
    ) = Room
        .databaseBuilder(appContext, UserDatabase::class.java, "user_db")
        .build() // The reason we can construct a database for the repo

    @Provides
    @Singleton
    fun provideYourDao(db: UserDatabase) = db.dao
}