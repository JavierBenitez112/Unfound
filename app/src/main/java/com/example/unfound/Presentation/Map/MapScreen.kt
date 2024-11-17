package com.example.unfound.Presentation.Map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unfound.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapRoute(
    onProfileClick: () -> Unit,
) {
    MapScreen1(
        onProfileClick = onProfileClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen1(
    onProfileClick: () -> Unit
) {
    val viewModel: MapScreenViewModel = viewModel(
        factory = MapScreenViewModel.Factory
    )

    val cameraPositionState = rememberCameraPositionState()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.markerPosition) {
        state.markerPosition?.let { position ->
            cameraPositionState.position = CameraPosition.fromLatLngZoom(position, 15f)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.unfoundbg),
                            contentDescription = "Logo",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onProfileClick() }) {
                        Icon(Icons.Default.Person, contentDescription = "User Icon")
                    }
                }
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                GoogleMap(
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(mapType = MapType.NORMAL),
                    uiSettings = MapUiSettings(zoomControlsEnabled = true)
                ) {
                    state.markerPosition?.let { position ->
                        Marker(
                            state = MarkerState(position = position),
                            title = "Ubicación seleccionada"
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = { /* Acción del primer botón */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ir")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            if (state.placesList.isNotEmpty()) {
                                val randomPlace = state.placesList.random()
                                val latLng = randomPlace.latLng
                                if (latLng != null) {
                                    viewModel.searchNearbyPlaces(latLng, 6000.0, listOf("restaurant", "tourist_attraction"))
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        Text("Otro Lugar")
                    }
                }
                state.selectedPlace?.let { place ->
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(16.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(8.dp)
                    ) {
                        Text(text = "Place ID: ${place.id}", style = MaterialTheme.typography.bodySmall)
                        Text(text = place.name, style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Direccion: ${place.address}", style = MaterialTheme.typography.bodySmall)
                        state.photoBitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "Place Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}