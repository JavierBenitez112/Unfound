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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.CircularBounds
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchNearbyRequest
import com.google.maps.android.compose.*


@Composable
fun MapScreen(placesClient: PlacesClient) {
    val cameraPositionState = rememberCameraPositionState()
    var markerPosition by remember { mutableStateOf<LatLng?>(null) }
    var selectedPlace by remember { mutableStateOf<Place?>(null) }
    var placesList by remember { mutableStateOf<List<Place>>(emptyList()) }

    LaunchedEffect(Unit) {
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        val center = LatLng(14.603769, -90.489415)
        val circle = CircularBounds.newInstance(center, 1000.0)
        val includedTypes = listOf("restaurant", "tourist_attraction")

        val searchNearbyRequest = SearchNearbyRequest.builder(circle, placeFields)
            .setIncludedTypes(includedTypes)
            .setMaxResultCount(10)
            .build()

        placesClient.searchNearby(searchNearbyRequest)
            .addOnSuccessListener { response ->
                placesList = response.places
                if (placesList.isNotEmpty()) {
                    val randomPlace = placesList.random()
                    val latLng = randomPlace.latLng
                    if (latLng != null) {
                        markerPosition = latLng
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                        fetchPlaceDetails(placesClient, randomPlace.id ?: "", setSelectedPlace = { selectedPlace = it })
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("MapScreen", "Place not found: ${exception.message}")
            }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = MapType.NORMAL),
            uiSettings = MapUiSettings(zoomControlsEnabled = true)
        ) {
            markerPosition?.let { position ->
                Marker(
                    state = MarkerState(position = position),
                    title = "Ubicación seleccionada"
                )
            }
        }

        selectedPlace?.let { place ->
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

private fun fetchPlaceDetails(placesClient: PlacesClient, placeId: String, setSelectedPlace: (Place) -> Unit) {
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)
    val request = FetchPlaceRequest.newInstance(placeId, placeFields)

    placesClient.fetchPlace(request)
        .addOnSuccessListener { response ->
            setSelectedPlace(response.place)
        }
        .addOnFailureListener { exception ->
            Log.e("MapScreen", "Place not found: ${exception.message}")
        }
}