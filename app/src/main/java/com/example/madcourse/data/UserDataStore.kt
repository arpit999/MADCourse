package com.example.madcourse.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.madcourse.data.KEYS.USER_ADDED
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Create the dataStore and give it a name same as user_pref
// Create some keys we will use them to store and retrieve the data
object KEYS {
    const val USER_PREFERENCES = "user_preferences"
    val USER_ADDED = booleanPreferencesKey("user_added")
}

val Context.dataStore by preferencesDataStore(name = KEYS.USER_PREFERENCES)

class UserDataStore @Inject constructor(
    @ApplicationContext val context: Context,
//    private val dataStore: DataStore<Preferences>
) {

    // Store user data
    // refer to the data store and using edit
    // we can store values using the keys
    suspend fun storeIsUserAdded(isUserAdded: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_ADDED] = isUserAdded
        }
    }

    // get the visibility of ScreenA
    val isUserAdded: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[USER_ADDED] ?: false
    }

}