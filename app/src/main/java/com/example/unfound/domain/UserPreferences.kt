package com.example.unfound.domain
import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun logIn()
    suspend fun logOut()
    fun authStatus(): Flow<Boolean>
}