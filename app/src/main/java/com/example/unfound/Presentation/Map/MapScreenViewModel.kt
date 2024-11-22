package com.example.unfound.Presentation.Map

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.*
import com.example.unfound.Data.repository.PlaceRepository
import com.example.unfound.Data.repository.Result
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unfound.Data.local.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class MapScreenViewModel(
    private val placeRepository: PlaceRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(MapScreenState())
    val state: StateFlow<MapScreenState> = _state.asStateFlow()

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)

    init {
        getUserLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        viewModelScope.launch {
            val locationTask: Task<Location> = fusedLocationClient.lastLocation
            locationTask.addOnSuccessListener { location: Location? ->
                location?.let {
                    val userLatLng = LatLng(it.latitude, it.longitude)
                    _state.value = _state.value.copy(markerPosition = userLatLng)
                    searchNearbyPlaces(userLatLng, 1000.0, listOf("restaurant", "tourist_attraction"))
                }
            }
        }
    }

    fun searchNearbyPlaces(center: LatLng, radius: Double, includedTypes: List<String>) {
        viewModelScope.launch {
            when (val result = placeRepository.searchNearbyPlaces(center, radius, includedTypes)) {
                is Result.Success -> {
                    _state.update { it.copy(placesList = result.data) }
                    if (result.data.isNotEmpty()) {
                        val randomPlace = result.data.random()
                        randomPlace.latLng?.let { latLng ->
                            _state.update { it.copy(markerPosition = latLng) }
                            fetchPlaceDetails(randomPlace.id ?: "")
                        }
                    }
                }
                is Result.Error -> {

                }
            }
        }
    }

    fun fetchPlaceDetails(placeId: String) {
        viewModelScope.launch {
            when (val result = placeRepository.fetchPlaceDetails(placeId)) {
                is Result.Success -> {
                    _state.update { it.copy(selectedPlace = result.data) }
                    fetchPlacePhoto(result.data)
                }
                is Result.Error -> {

                }
            }
        }
    }

    private fun fetchPlacePhoto(place: Place) {
        val photoMetadata = place.photoMetadatas?.firstOrNull()
        if (photoMetadata != null) {
            viewModelScope.launch {
                when (val result = placeRepository.fetchPlacePhoto(photoMetadata)) {
                    is Result.Success -> {
                        _state.update { it.copy(photoBitmap = result.data) }
                    }
                    is Result.Error -> {

                    }
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val placesClient = com.google.android.libraries.places.api.Places.createClient(context)
                val repository = PlaceRepository(placesClient)
                MapScreenViewModel(
                    placeRepository = repository,
                    application = context
                )
            }
        }
    }
}