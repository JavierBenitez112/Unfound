package com.example.unfound.Presentation.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onBackClick: () -> Unit
) {
    composable<ProfileDestination> {
        ProfileRoute(onBackClick = onBackClick)
    }
}