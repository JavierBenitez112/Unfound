package com.example.unfound.DirectionsScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class Point(val latitude: Double, val longitude: Double) // Definimos la clase Point si no está ya definida

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceScreen(
    placeName: String,
    placeDescription: String,
    mapLocation: Point, // Using Point instead of LatLng
    imageResId: Int // Reemplazamos la URL por un ID de recurso
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Content section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Place name
                Text(
                    text = placeName,
                    style = MaterialTheme.typography.titleLarge
                )

                // Place description
                Text(
                    text = placeDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Image using painterResource (local image)
                Image(
                    painter = painterResource(id = imageResId), // Cargar imagen local
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )

                // Button
                Button(
                    onClick = { /*TODO: Add action*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = "Otro Lugar")
                }
            }
        }
    }
}

