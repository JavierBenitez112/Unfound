package com.example.unfound.Presentation.Map

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place

data class MapScreenState(
    val markerPosition: LatLng? = null,
    val selectedPlace: Place? = null,
    val placesList: List<Place> = emptyList(),
    val photoBitmap: Bitmap? = null

)