package com.example.unfound.Presentation.SignIn

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Variable para almacenar el estado de autenticación
    var signInState: SignInState = SignInState.Idle
        private set

    // Función para iniciar sesión
    fun signIn(email: String, password: String, context: Context, onSignInSuccess: () -> Unit) {
        viewModelScope.launch {
            signInState = SignInState.Loading
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    signInState = if (task.isSuccessful) {
                        Toast.makeText(context, "Se inició sesión correctamente", Toast.LENGTH_SHORT).show()
                        onSignInSuccess()
                        SignInState.Success
                    } else {
                        Toast.makeText(context, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
                        SignInState.Error("Datos Incorrectos")
                    }
                }
        }
    }
}

// Clase para representar el estado de la autenticación
sealed class SignInState {
    data object Idle : SignInState()
    data object Loading : SignInState()
    data object Success : SignInState()
    data class Error(val message: String) : SignInState()
}
