package com.example.unfound.Data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStorePlaces(
    private val dataStore: DataStore<Preferences>
) {
    private val userNameKey = stringPreferencesKey("user_name")
    private val visitedPlacesKey = stringSetPreferencesKey("visited_places")

    suspend fun setUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[userNameKey] = name
        }
    }

    fun getUserName(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[userNameKey]
    }

    suspend fun addVisitedPlace(placeId: String) {
        dataStore.edit { preferences ->
            val currentPlaces = preferences[visitedPlacesKey] ?: emptySet()
            preferences[visitedPlacesKey] = currentPlaces + placeId
        }
    }

    fun getVisitedPlaces(): Flow<Set<String>> = dataStore.data.map { preferences ->
        preferences[visitedPlacesKey] ?: emptySet()
    }
}