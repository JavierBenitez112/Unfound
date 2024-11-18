package com.example.unfound.Presentation.SignIn

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unfound.Data.repository.FirebaseAuthRepository
import com.example.unfound.Presentation.SignIn.SignInState.Success
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: FirebaseAuthRepository) : ViewModel() {

    var signInState: SignInState by mutableStateOf(SignInState.Idle)

    fun signIn(email: String, password: String, context: Context, onSignInClick: () -> Unit) {
        signInState = SignInState.Loading

        viewModelScope.launch {
            val result = authRepository.signin(email, password)
            if (result) {
                signInState = Success
                onSignInClick()
            } else {
                signInState = SignInState.Error("Ingrese credenciales válidas")
            }
        }
    }
}

