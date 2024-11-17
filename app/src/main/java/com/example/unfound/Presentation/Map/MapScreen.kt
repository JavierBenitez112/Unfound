package com.example.unfound.Presentation.Map

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unfound.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.CircularBounds
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchNearbyRequest
import com.google.maps.android.compose.*

@Composable
fun MapRoute(
    onProfileClick: () -> Unit,
) {
    MapScreen1(
        onProfileClick = onProfileClick,
        placesClient = Places.createClient(LocalContext.current)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen1(
    placesClient: PlacesClient,
    onProfileClick: () -> Unit
) {
    val cameraPositionState = rememberCameraPositionState()
    var markerPosition by remember { mutableStateOf<LatLng?>(null) }
    var selectedPlace by remember { mutableStateOf<Place?>(null) }
    var placesList by remember { mutableStateOf<List<Place>>(emptyList()) }
    var photoBitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(Unit) {
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        val center = LatLng(14.603769, -90.489415)
        val circle = CircularBounds.newInstance(center, 6000.0)
        val includedTypes = listOf("restaurant","tourist_attraction")

        val searchNearbyRequest = SearchNearbyRequest.builder(circle, placeFields)
            .setIncludedTypes(includedTypes)
            .setMaxResultCount(20)
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
                        fetchPlaceDetails(placesClient, randomPlace.id ?: "", setSelectedPlace = { selectedPlace = it }, setPhotoBitmap = { photoBitmap = it })
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("MapScreen1", "Place not found: ${exception.message}")
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
                    markerPosition?.let { position ->
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
                            if (placesList.isNotEmpty()) {
                                val randomPlace = placesList.random()
                                val latLng = randomPlace.latLng
                                if (latLng != null) {
                                    markerPosition = latLng
                                    cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                                    fetchPlaceDetails(placesClient, randomPlace.id ?: "", setSelectedPlace = { selectedPlace = it }, setPhotoBitmap = { photoBitmap = it })
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
                selectedPlace?.let { place ->
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
                        photoBitmap?.let {
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

private fun fetchPlaceDetails(placesClient: PlacesClient, placeId: String, setSelectedPlace: (Place) -> Unit, setPhotoBitmap: (Bitmap?) -> Unit) {
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.PHOTO_METADATAS)
    val request = FetchPlaceRequest.newInstance(placeId, placeFields)

    placesClient.fetchPlace(request)
        .addOnSuccessListener { response ->
            val place = response.place
            setSelectedPlace(place)
            val photoMetadata = place.photoMetadatas?.firstOrNull()
            if (photoMetadata != null) {
                val photoRequest = FetchPhotoRequest.builder(photoMetadata)
                    .build()
                placesClient.fetchPhoto(photoRequest)
                    .addOnSuccessListener { fetchPhotoResponse ->
                        val bitmap = fetchPhotoResponse.bitmap
                        setPhotoBitmap(bitmap)
                    }
                    .addOnFailureListener { exception ->
                        Log.e("MapScreen1", "Photo not found: ${exception.message}")
                        setPhotoBitmap(null)
                    }
            } else {
                setPhotoBitmap(null)
            }
        }
        .addOnFailureListener { exception ->
            Log.e("MapScreen1", "Place not found: ${exception.message}")
            setPhotoBitmap(null)
        }
}

@Composable
@Preview
fun MapScreenPreview1() {
    MapScreen1(
        placesClient = Places.createClient(LocalContext.current),
        onProfileClick = {}
    )
}