package com.example.unfound.Presentation.profile

sealed class ProfileScreenEvent {
    data class NameChange(val name: String) : ProfileScreenEvent()
    object SaveName : ProfileScreenEvent()
}