package com.example.unfound.Presentation.Map

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unfound.Data.repository.PlaceRepository
import com.example.unfound.Data.repository.Result
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapScreenViewModel(
    private val placeRepository: PlaceRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MapScreenState())
    val state: StateFlow<MapScreenState> = _state.asStateFlow()

    init {
        searchNearbyPlaces(LatLng(14.603769, -90.489415), 6000.0, listOf("restaurant", "tourist_attraction"))
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
                    // Handle error
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
                    // Handle error
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
                        // Handle error
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
                    savedStateHandle = this.createSavedStateHandle()
                )
            }
        }
    }
}