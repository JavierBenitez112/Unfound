package com.example.unfound.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unfound.R

@Composable
fun LoginRoute(
    onLoginClick: () -> Unit,
) {
    LoginScreen(
        onLoginClick = onLoginClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
) {
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
                .border(8.dp, MaterialTheme.colorScheme.primary)  // Usando el color primario
                .padding(8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center // Centrar contenido
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

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
                Text(
                    text = "Know more places",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.unfoundbg),
            contentDescription = "Logo",
            modifier = Modifier.size(250.dp)
        )

        // Botones de Login y Crear cuenta modificar accion despues para redirigir a la pantalla de login
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón de Login
            Button(
                onClick = { /* Acción de login */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "Login Icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(text = "Login",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground)
            }
            // Botón de Create Account
            Button(
                onClick = { /* Acción de crear cuenta */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "Create Account",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}


@Composable
@Preview
fun LoginScreenPreview1() {
    LoginScreen(onLoginClick = {})  // Lambda vacía para onLoginClick
}