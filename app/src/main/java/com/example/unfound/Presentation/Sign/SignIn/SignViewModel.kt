package com.uvg.ejercicioslabs.ejercicios.firebase.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unfound.Data.repository.FirebaseLoginRepository
import com.example.unfound.Presentation.Sign.SignIn.SignInEvent
import com.example.unfound.Presentation.Sign.SignIn.SignInScreenState
import com.example.unfound.domain.repository.LoginRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SignInScreenState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: SignInEvent) {
        _uiState.value = _uiState.value.copy(
            successMessage = null,
            errorMessage = null
        )

        when (event) {
            is SignInEvent.EmailChanged -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }

            is SignInEvent.PasswordChanged -> {
                _uiState.value = _uiState.value.copy(password = event.password)
            }

            SignInEvent.LoginClicked -> login()
            SignInEvent.CreateAccountClicked -> createAccount()
        }
    }

    private fun login() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val success = loginRepository.login(_uiState.value.email, _uiState.value.password)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                successMessage = if (success) "Login successful" else null,
                errorMessage = if (!success) "Login failed" else null,
                authStatus = success
            )
        }
    }

    private fun createAccount() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val success = loginRepository.createUser(_uiState.value.email, _uiState.value.password)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                successMessage = if (success) "Account created successfully" else null,
                errorMessage = if (!success) "Failed to create account" else null,
                authStatus = success
            )
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val loginRepository = FirebaseLoginRepository()
                SignViewModel(loginRepository)
            }
        }
    }
}