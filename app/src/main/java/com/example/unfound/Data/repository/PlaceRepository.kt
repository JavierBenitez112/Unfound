package com.example.unfound.Data.repository

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.CircularBounds
import com.google.android.libraries.places.api.model.PhotoMetadata
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchNearbyRequest
import kotlinx.coroutines.tasks.await

sealed class DataError {
    object NO_INTERNET : DataError()
    object GENERIC_ERROR : DataError()
}

sealed class Result<out T, out E> {
    data class Success<out T>(val data: T) : Result<T, Nothing>()
    data class Error<out E>(val error: E) : Result<Nothing, E>()
}

class PlaceRepository(private val placesClient: PlacesClient) {

    suspend fun searchNearbyPlaces(center: LatLng, radius: Double, includedTypes: List<String>): Result<List<Place>, DataError> {
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        val circle = CircularBounds.newInstance(center, radius)

        val searchNearbyRequest = SearchNearbyRequest.builder(circle, placeFields)
            .setIncludedTypes(includedTypes)
            .setMaxResultCount(10)
            .build()

        return try {
            val response = placesClient.searchNearby(searchNearbyRequest).await()
            Result.Success(response.places)
        } catch (exception: Exception) {
            Result.Error(DataError.GENERIC_ERROR)
        }
    }

    suspend fun fetchPlaceDetails(placeId: String): Result<Place, DataError> {
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.PHOTO_METADATAS)
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)

        return try {
            val response = placesClient.fetchPlace(request).await()
            Result.Success(response.place)
        } catch (exception: Exception) {
            Result.Error(DataError.GENERIC_ERROR)
        }
    }

    suspend fun fetchPlacePhoto(photoMetadata: PhotoMetadata): Result<Bitmap, DataError> {
        val request = FetchPhotoRequest.builder(photoMetadata).build()

        return try {
            val response = placesClient.fetchPhoto(request).await()
            Result.Success(response.bitmap)
        } catch (exception: Exception) {
            Result.Error(DataError.GENERIC_ERROR)
        }
    }
}