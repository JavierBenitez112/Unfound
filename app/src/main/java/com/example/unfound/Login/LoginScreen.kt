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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unfound.R


@Composable
fun LoginScreen1() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Box para el título "UNFOUND"
        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colorScheme.primary)  // Usando el color primario
                .padding(8.dp)
        ) {
            Text(
                text = "UNFOUND",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary  // Texto usando el color de texto sobre el color primario
            )
        }

        // Subtítulo "Know more places"
        Text(
            text = "Know more places",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Espacio reservado para la imagen
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.unfound), // Reemplaza con la imagen cargada
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp)
        )

        // Botones de Login y Crear cuenta
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón de Login
            Button(
                onClick = { /* Acción de login */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary  // Usando el color primario
                )
            ) {
                Text(text = "Login", color = MaterialTheme.colorScheme.onPrimary)
            }
            // Botón de Create Account
            Button(
                onClick = { /* Acción de crear cuenta */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary  // Usando el color primario
                )
            ) {
                Text(text = "Create Account", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}


@Composable
@Preview
fun LoginScreenPreview1() {
    LoginScreen1()
}