package com.example.unfound.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unfound.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.unfoundbg), // Cambiar por el logo que desees
                            contentDescription = "Logo",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Acción de menú */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Acción de usuario */ }) {
                        Icon(Icons.Default.Person, contentDescription = "User Icon")
                    }
                }
            )
        },
        content = { padding ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Imagen de olas en el fondo
                Image(
                    painter = painterResource(id = R.drawable.image9), // Reemplazar con tu imagen de olas
                    contentDescription = "Waves",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter), // Alinear la imagen en la parte inferior
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )

                // Contenido principal
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Título
                    Text(
                        text = "¿Qué vamos a hacer hoy?",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 32.dp)
                    )

                    // Botones de acción
                    Column(
                        modifier = Modifier.padding(top = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { /* Acción para Descubre Restaurantes */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(text = "Descubre Restaurantes", color = Color.White)
                        }

                        Button(
                            onClick = { /* Acción para Descubre Lugares */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = "Descubre Lugares", color = Color.White)
                        }

                        Button(
                            onClick = { /* Acción para Sorprendeme */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            Text(text = "Sorprendeme", color = Color.White)
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
