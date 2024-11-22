package com.example.unfound.Presentation.SignIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unfound.R
import com.uvg.ejercicioslabs.ejercicios.firebase.presentation.login.SignViewModel
import com.example.unfound.Presentation.Sign.SignIn.SignInEvent

@Composable
fun SignInRoute(
    viewModel: SignViewModel = viewModel(factory = SignViewModel.Factory),
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignInScreen(
        email = uiState.email,
        password = uiState.password,
        successMessage = uiState.successMessage,
        errorMessage = uiState.errorMessage,
        isLoading = uiState.isLoading,
        onEvent = viewModel::onEvent,
        onSignInClick = onSignInClick,
        onForgotPasswordClick = onForgotPasswordClick
    )
}

@Composable
fun SignInScreen(
    email: String,
    password: String,
    successMessage: String?,
    errorMessage: String?,
    isLoading: Boolean,
    onEvent: (SignInEvent) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(successMessage) {
        if (successMessage != null) {
            onSignInClick()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(8.dp, MaterialTheme.colorScheme.primary)
                .padding(8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "UNFOUND",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = MaterialTheme.colorScheme.onBackground,
                            offset = Offset(2f, 2f),
                            blurRadius = 5f
                        )
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.unfoundbg),
            contentDescription = "Logo",
            modifier = Modifier.size(175.dp)
        )

        SignInForm(
            email = email,
            password = password,
            successMessage = successMessage,
            errorMessage = errorMessage,
            isLoading = isLoading,
            onEvent = onEvent,
            onSignInClick = onSignInClick,
            onForgotPasswordClick = onForgotPasswordClick
        )
    }
}

@Composable
fun SignInForm(
    email: String,
    password: String,
    successMessage: String?,
    errorMessage: String?,
    isLoading: Boolean,
    onEvent: (SignInEvent) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { onEvent(SignInEvent.EmailChanged(it)) },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true,
            enabled = !isLoading
        )

        OutlinedTextField(
            value = password,
            onValueChange = { onEvent(SignInEvent.PasswordChanged(it)) },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            enabled = !isLoading,
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (successMessage != null || errorMessage != null) {
            Text(
                text = successMessage ?: errorMessage ?: "",
                color = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onEvent(SignInEvent.LoginClicked) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            enabled = !isLoading && successMessage == null
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
@Preview
fun SignInScreenPreview() {
    SignInScreen(
        email = "",
        password = "",
        successMessage = null,
        errorMessage = null,
        isLoading = false,
        onEvent = {},
        onSignInClick = {},
        onForgotPasswordClick = {}
    )
}