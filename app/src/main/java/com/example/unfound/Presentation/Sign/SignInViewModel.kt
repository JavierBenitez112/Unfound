package com.example.unfound.Presentation.Sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unfound.domain.UserPreferences
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unfound.Data.local.DataStoreUserPrefs
import com.example.unfound.dataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class SignInViewModel(
    private val preferences: UserPreferences
) : ViewModel() {

    val authStatus = preferences.authStatus()
        .onStart {
            delay(1000)
        }
        .map { isLogged ->
            if (isLogged) {
                SignStatus.Authenticated
            } else {
                SignStatus.NonAuthenticated
            }
        }
        .catch { error ->
            println(error)
            SignStatus.NonAuthenticated
        }
        .onEach { item -> println(item) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            SignStatus.Loading
        )



    fun authenticateUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            preferences.logIn()
            onSuccess()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                SignInViewModel(
                    preferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }
}