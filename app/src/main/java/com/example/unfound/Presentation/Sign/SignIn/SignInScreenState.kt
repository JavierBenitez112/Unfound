package com.example.unfound.Presentation.Sign.SignIn

data class SignInScreenState(
    val email: String = "",
    val password: String = "",
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val authStatus: Boolean = false,
    val isLoading: Boolean = false
)