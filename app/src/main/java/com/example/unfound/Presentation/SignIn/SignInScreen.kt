package com.example.unfound.Presentation.SignIn

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unfound.R

@Composable
fun SignInRoute(
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    SignInScreen(
        onSignInClick = onSignInClick,
        onForgotPasswordClick = onForgotPasswordClick
    )
}

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    viewModel: SignInViewModel = SignInViewModel()
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "UNFOUND",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.unfoundbg),
            contentDescription = "Logo",
            modifier = Modifier.size(175.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Button(
            onClick = {
                viewModel.signIn(email, password, context, onSignInClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = "Forgot Password?",
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable { onForgotPasswordClick() }
        )

        when (viewModel.signInState) {
            is SignInState.Loading -> CircularProgressIndicator()
            is SignInState.Error -> Text(
                text = (viewModel.signInState as SignInState.Error).message,
                color = Color.Red
            )
            is SignInState.Success -> { /* No hace nada, el éxito se maneja en onSignInClick */ }
            else -> Unit
        }
    }
}

@Composable
@Preview
fun SignInScreenPreview() {
    SignInScreen(
        onSignInClick = {},
        onForgotPasswordClick = {}
    )
}
