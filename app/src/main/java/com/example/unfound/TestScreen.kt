package com.example.unfound

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unfound.Presentation.Map.MapScreenViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen(viewModel: MapScreenViewModel = viewModel()) {
    val cameraPositionState = rememberCameraPositionState()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        val center = LatLng(14.603769, -90.489415)
        val radius = 1000.0
        val includedTypes = listOf("restaurant", "tourist_attraction")
        viewModel.searchNearbyPlaces(center, radius, includedTypes)
    }

    Box(modifier = Modifier.fillMaxSize()) {
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
                    .align(Alignment.TopCenter)
                    .padding(16.dp)
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Text(text = "Place ID: ${place.id}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Name: ${place.name}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Address: ${place.address}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}