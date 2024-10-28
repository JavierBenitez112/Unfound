package com.example.unfound.Data.source

import com.example.unfound.Data.model.Location

object LocationsDb {
    fun getLocations(): List<Location> {
        return listOf(
            Location("First Location", 14.603722, -90.489250),
            Location("New Location", 41.0082, 28.9784)
        )
    }
}