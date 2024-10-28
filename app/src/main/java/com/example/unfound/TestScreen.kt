package com.example.unfound

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(placesClient: PlacesClient) {
    val placeId = "ChIJD7fiBh9u5kcRYJSMaMOCCwQ" // ID de ejemplo: Torre Eiffel
    val cameraPositionState = rememberCameraPositionState()
    var markerPosition by remember { mutableStateOf<LatLng?>(null) }

    // Obtener lugar por ID
    LaunchedEffect(placeId) {
        val placeRequest = FetchPlaceRequest.builder(placeId, listOf(Place.Field.ID, Place.Field.LAT_LNG)).build()
        placesClient.fetchPlace(placeRequest).addOnSuccessListener { response ->
            val place = response.place
            val latLng = place.latLng
            if (latLng != null) {
                markerPosition = latLng
                cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
            }
        }.addOnFailureListener { exception ->
            Log.e("MapScreen", "Place not found: ${exception.message}")
        }
    }

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
}

@Preview
@Composable
fun MapScreenPreview() {
    val context = LocalContext.current
    val placesClient = Places.createClient(context)

    MapScreen(placesClient = placesClient)
}
