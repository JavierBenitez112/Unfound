package com.example.unfound.domain.model

data class Place(
    val id: String,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double
)