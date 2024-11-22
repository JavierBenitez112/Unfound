package com.example.unfound.Presentation.profile

sealed interface DataStoreScreenEvent {
    data class NameChange(val name: String): DataStoreScreenEvent
    data object SaveName: DataStoreScreenEvent
}