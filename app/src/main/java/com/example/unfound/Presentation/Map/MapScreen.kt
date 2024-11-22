package com.example.unfound.Presentation.Map

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unfound.Presentation.profile.ProfileScreenViewModel
import com.example.unfound.Presentation.profile.VisitedPlace
import com.example.unfound.R
import com.google.android.gms.maps.model.CameraPosition
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
    onProfileClick: () -> Unit,
    profileViewModel: ProfileScreenViewModel = viewModel()
) {
    val viewModel: MapScreenViewModel = viewModel(
        factory = MapScreenViewModel.Factory
    )

    val cameraPositionState = rememberCameraPositionState()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

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
                navigationIcon = {
                    Box(modifier = Modifier.alpha(0f)) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
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

                state.selectedPlace?.let { place ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = place.name,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = place.address,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        state.photoBitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "Place Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .padding(bottom = 16.dp)
                            )
                        }
                        Button(
                            onClick = {
                                val gmmIntentUri = Uri.parse("geo:0,0?q=${place.address}")
                                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                mapIntent.setPackage("com.google.android.apps.maps")
                                context.startActivity(mapIntent)
                                profileViewModel.addVisitedPlace(
                                    VisitedPlace(
                                        id = place.id ?: "",
                                        name = place.name ?: "",
                                        address = place.address ?: "",
                                        photoBitmap = state.photoBitmap
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Ir")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                state.markerPosition?.let { userPosition ->
                                    viewModel.searchNearbyPlaces(userPosition, 2000.0, listOf("restaurant", "tourist_attraction"))
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
                }
            }
        }
    )
}