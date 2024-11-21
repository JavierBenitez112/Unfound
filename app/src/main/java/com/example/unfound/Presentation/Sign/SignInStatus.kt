package com.example.unfound.Presentation.Sign

interface SignStatus {
    data object Loading: SignStatus
    data object Authenticated: SignStatus
    data object NonAuthenticated: SignStatus
}