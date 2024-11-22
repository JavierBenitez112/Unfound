package com.example.unfound.domain.repository

interface LoginRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun createUser(email: String, password: String): Boolean
    suspend fun isUserLoggedIn(): Boolean
}