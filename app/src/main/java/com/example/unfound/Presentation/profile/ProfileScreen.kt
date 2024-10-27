package com.example.unfound.Presentation.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unfound.R
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalSoftwareKeyboardController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val visitedPlaces = listOf(
        VisitedPlace("Restaurante Giratorio", "Reseña del restaurante", R.drawable.image1, 4),
        VisitedPlace("Zoologico La Aurora", "Reseña del zoologico", R.drawable.image8, 5),
    )
    var userName by remember { mutableStateOf("Juan Francisco") }
    var isEditing by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.unfoundbg), // Cambia por tu logo
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
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile), // Cambia por tu recurso de imagen
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (isEditing) {
                        OutlinedTextField(
                            value = userName,
                            onValueChange = { userName = it },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            placeholder = { Text("Introduce tu nombre") },
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    isEditing = false
                                    keyboardController?.hide()
                                }
                            )
                        )
                    } else {
                        Text(
                            text = userName,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                    IconButton(
                        onClick = { isEditing = !isEditing } // Alterna entre vista y edición
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Name")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Acción de cambiar foto */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Icon"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Cambiar foto de perfil")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Lugares visitados",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(visitedPlaces) { place ->
                        VisitedPlaceCard(
                            name = place.name,
                            description = place.description,
                            imageResId = place.imageResId,
                            starCount = place.starCount
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun VisitedPlaceCard(
    name: String,
    description: String,
    imageResId: Int,
    starCount: Int
) {
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
            // Nombre del lugar
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Descripción del lugar
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Imagen del lugar
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Estrellas de valoración
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        modifier = Modifier.size(24.dp),
                        tint = if (index < starCount) Color.Black else Color.Gray
                    )
                }
            }
        }
    }
}


data class VisitedPlace(
    val name: String,
    val description: String,
    val imageResId: Int,
    val starCount: Int
)


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    MaterialTheme {
        ProfileScreen()
    }
}
