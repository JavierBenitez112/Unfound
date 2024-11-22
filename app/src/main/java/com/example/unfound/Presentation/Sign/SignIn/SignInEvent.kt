package com.example.unfound.Presentation.Sign.SignIn

sealed interface SignInEvent {
    data class EmailChanged(val email: String) : SignInEvent
    data class PasswordChanged(val password: String) : SignInEvent
    data object LoginClicked : SignInEvent
    data object CreateAccountClicked : SignInEvent
}
