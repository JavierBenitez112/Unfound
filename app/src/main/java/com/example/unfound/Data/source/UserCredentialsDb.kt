package com.example.unfound.Data.source

import com.example.unfound.Data.model.UserCredentials

object UserCredentialsDb {
    private val credentials = listOf(
        UserCredentials("user1@example.com", "password1"),
        UserCredentials("user2@example.com", "password2")
    )

    fun isValid(email: String, password: String): Boolean {
        return credentials.any { it.email == email && it.password == password }
    }
}