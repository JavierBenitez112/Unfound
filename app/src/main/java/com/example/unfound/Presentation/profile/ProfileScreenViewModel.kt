package com.example.unfound.Presentation.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.unfound.Data.local.DataStoreManager
import com.example.unfound.Data.repository.PlaceRepository
import com.example.unfound.Data.repository.Result
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStoreManager = DataStoreManager(application)
    private val placesClient: PlacesClient = Places.createClient(application)
    private val placeRepository = PlaceRepository(placesClient)

    private val _visitedPlaces = MutableStateFlow<List<VisitedPlace>>(emptyList())
    val visitedPlaces: StateFlow<List<VisitedPlace>> = _visitedPlaces

    init {
        viewModelScope.launch {
            dataStoreManager.visitedPlaces.collect { placeIds ->
                val places = placeIds.mapNotNull { id ->
                    when (val result = placeRepository.fetchPlaceDetails(id)) {
                        is Result.Success -> {
                            val placeEntity = result.data
                            val photoBitmap = placeEntity.photoMetadatas?.firstOrNull()?.let { photoMetadata ->
                                when (val photoResult = placeRepository.fetchPlacePhoto(photoMetadata)) {
                                    is Result.Success -> photoResult.data
                                    is Result.Error -> null
                                }
                            }
                            VisitedPlace(
                                id = placeEntity.id,
                                name = placeEntity.name,
                                address = placeEntity.address,
                                photoBitmap = photoBitmap
                            )
                        }
                        is Result.Error -> null
                    }
                }
                _visitedPlaces.value = places
            }
        }
    }

    fun addVisitedPlace(place: VisitedPlace) {
        viewModelScope.launch {
            dataStoreManager.addVisitedPlace(place.id)
            _visitedPlaces.value = _visitedPlaces.value + place
        }
    }
}