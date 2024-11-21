package com.example.unfound.Data.network.dto

import com.example.unfound.Data.local.entity.PlaceEntity
import com.example.unfound.domain.model.Place
import kotlinx.serialization.Serializable

@Serializable
data class EntryDto(
    val id: String,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double
)

fun EntryDto.mapToPlaceModel(): Place {
    return Place(
        id = id,
        name = name,
        address = address,
        lat = lat,
        lng = lng
    )
}

fun EntryDto.mapToPlaceEntity(): PlaceEntity {
    return PlaceEntity(
        id = id,
        name = name,
        address = address,
        lat = lat,
        lng = lng
    )
}