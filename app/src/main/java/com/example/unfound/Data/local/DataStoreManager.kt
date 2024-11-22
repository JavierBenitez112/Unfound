package com.example.unfound.Data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("visited_places")

class DataStoreManager(private val context: Context) {

    private val VISITED_PLACES_KEY = stringSetPreferencesKey("visited_places")

    val visitedPlaces: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[VISITED_PLACES_KEY] ?: emptySet()
        }

    suspend fun addVisitedPlace(placeId: String) {
        context.dataStore.edit { preferences ->
            val currentPlaces = preferences[VISITED_PLACES_KEY] ?: emptySet()
            preferences[VISITED_PLACES_KEY] = currentPlaces + placeId
        }
    }
}