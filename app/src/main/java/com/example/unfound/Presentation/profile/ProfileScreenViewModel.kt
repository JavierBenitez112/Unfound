package com.example.unfound.Presentation.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProfileScreenViewModel : ViewModel() {
    private val _visitedPlaces = MutableStateFlow<List<VisitedPlace>>(emptyList())
    val visitedPlaces: StateFlow<List<VisitedPlace>> = _visitedPlaces

    fun addVisitedPlace(place: VisitedPlace) {
        _visitedPlaces.update { currentPlaces ->
            currentPlaces + place
        }
    }
}