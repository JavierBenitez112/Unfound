package com.example.unfound.Data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlaceEntity(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double
)

fun PlaceEntity.mapToPlaceModel(): PlaceModel {
    return PlaceModel(
        id = id,
        name = name,
        address = address,
        lat = lat,
        lng = lng
    )
}