package com.example.madcourse.di

import android.content.Context
import androidx.room.Room
import com.example.madcourse.data.UserDataStore
import com.example.madcourse.domain.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext appContext: Context
    ) = Room
        .databaseBuilder(appContext, UserDatabase::class.java, "user_db")
        .build() // The reason we can construct a database for the repo

    @Provides
    @Singleton
    fun provideYourDao(db: UserDatabase) = db.dao

    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): UserDataStore {
        return UserDataStore(context)
    }

    /**
     * TODO QUESTION: How do I access this method in UserDataStore?
     */
//    @Provides
//    @Singleton
//    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
//        return PreferenceDataStoreFactory.create(
//            corruptionHandler = ReplaceFileCorruptionHandler(
//                produceNewData = { emptyPreferences() }
//            ),
//            migrations = listOf(SharedPreferencesMigration(appContext, USER_PREFERENCES)),
//            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
//            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
//        )
//    }

}