package com.example.unfound.Presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SignUpViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _signUpSuccess = MutableStateFlow(false)
    val signUpSuccess: StateFlow<Boolean> = _signUpSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun signUpUser() {
        viewModelScope.launch {
            // Simulación de validación básica y registro
            if (_email.value.isEmpty() || _password.value.isEmpty()) {
                _errorMessage.value = "Por favor, completa todos los campos."
                _signUpSuccess.value = false
            } else {
                _signUpSuccess.value = true
                _errorMessage.value = null
            }
        }
    }
}
