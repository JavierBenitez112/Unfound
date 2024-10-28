package com.example.unfound.Data.source

import com.example.unfound.Data.model.VisitedPlace
import com.example.unfound.R

object VisitedPlacesDb {
    private val places = listOf(
        VisitedPlace("Restaurante Giratorio", "Reseña del restaurante", R.drawable.image1, 4),
        VisitedPlace("Zoologico La Aurora", "Reseña del zoologico", R.drawable.image8, 5)
    )

    fun getAllPlaces(): List<VisitedPlace> {
        return places
    }
}