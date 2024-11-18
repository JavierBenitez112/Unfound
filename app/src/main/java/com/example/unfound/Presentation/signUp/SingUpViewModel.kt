package com.example.unfound.Presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.unfound.Data.repository.FirebaseAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            val success = authRepository.createUser(email, password)
            _signUpState.value = if (success) {
                SignUpState.Success
            } else {
                SignUpState.Error("Error al registrarse. Verifica los datos.")
            }
        }
    }
}


