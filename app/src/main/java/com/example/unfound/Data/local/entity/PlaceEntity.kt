package com.example.unfound.Data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unfound.domain.model.Place


@Entity
data class PlaceEntity(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double
)

fun PlaceEntity.mapToPlaceModel(): Place {
    return Place(
        id = id,
        name = name,
        address = address,
        lat = lat,
        lng = lng
    )
}
