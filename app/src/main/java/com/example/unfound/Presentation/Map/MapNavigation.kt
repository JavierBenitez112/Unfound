package com.example.unfound.Presentation.Map

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

//Sustituirla por una data class

@Serializable
data object MapDestination

fun NavGraphBuilder.MapScreen(
    onProfileClick: () -> Unit
) {
    composable<MapDestination> {
        MapRoute(
            onProfileClick = onProfileClick
        )
    }
}