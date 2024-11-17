package com.example.unfound

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.unfound.Data.repository.PlaceRepository
import com.example.unfound.Presentation.Map.MapScreen
import com.example.unfound.Presentation.Map.MapScreen1
import com.example.unfound.ui.theme.UnfoundTheme
import com.google.android.libraries.places.api.Places

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define a variable to hold the Places API key.
        val apiKey = "AIzaSyCN1OudTm0VY2ZgOhNc3gtYt0qSk7IE0v4"

        // Log an error if apiKey is not set.
        if (apiKey.isEmpty() || apiKey == "DEFAULT_API_KEY") {
            Log.e("Places test", "No API key")
            finish()
            return
        }

        // Initialize the SDK
        Places.initializeWithNewPlacesApiEnabled(applicationContext, apiKey)

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(this)
        val repository = PlaceRepository(placesClient)

        setContent {
            UnfoundTheme {
                MapScreen1(
                    onProfileClick = {}
                )
            }
        }
    }
}