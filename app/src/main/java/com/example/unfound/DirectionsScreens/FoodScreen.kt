package com.example.unfound.DirectionsScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

data class FoodScreen(
    val nombre: String,
    val descripcion: String,
    val imagenResId: Int // ID del recurso de imagen (imagen local)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestauranteListScreen(restaurantes: List<FoodScreen>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(restaurantes) { restaurante ->
            RestauranteCard(restaurante)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestauranteCard(foodScreen: FoodScreen) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Nombre del foodScreen
            Text(
                text = foodScreen.nombre,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Descripción del foodScreen
            Text(
                text = foodScreen.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Imagen del foodScreen usando un recurso local (painterResource)
            Image(
                painter = painterResource(id = foodScreen.imagenResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
    }
}
