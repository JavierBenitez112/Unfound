package com.example.unfound.Data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.unfound.domain.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreUserPrefs(
    private val dataStore: DataStore<Preferences>
): UserPreferences {
    private val loggedKey = booleanPreferencesKey("logged")
    private val nameKey = stringPreferencesKey("name")

    override suspend fun logIn() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = true
        }
    }

    override suspend fun logOut() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = false
        }
    }

    override fun authStatus(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[loggedKey] ?: false
    }

    suspend fun saveName(name: String) {
        dataStore.edit { preferences ->
            preferences[nameKey] = name
        }
    }

    fun getName(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[nameKey]
    }
}