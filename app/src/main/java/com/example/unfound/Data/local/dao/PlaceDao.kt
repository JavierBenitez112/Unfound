package com.example.unfound.Data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.unfound.Data.local.entity.PlaceEntity

@Dao
interface PlaceDao {

    @Query("SELECT * FROM PlaceEntity")
    suspend fun getNearbyPlaces(): List<PlaceEntity>

    @Upsert
    suspend fun insertPlaces(places: List<PlaceEntity>)

    @Query("SELECT * FROM PlaceEntity WHERE id = :id")
    suspend fun getOnePlace(id: Int): PlaceEntity

}