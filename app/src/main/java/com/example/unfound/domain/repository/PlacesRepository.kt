package com.example.unfound.domain.repository

import android.graphics.Bitmap
import com.example.unfound.domain.model.DataError
import com.example.unfound.domain.model.Place
import com.example.unfound.domain.network.util.Result
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.PhotoMetadata

interface PlacesRepository {
    suspend fun getNearbyPlaces(center: LatLng, radius: Double, includedTypes: List<String>): Result<List<Place>, DataError>
    suspend fun getOnePlace(id: String): Result<Place, DataError>
    suspend fun fetchPlacePhoto(photoMetadata: PhotoMetadata): Result<Bitmap, DataError>
}