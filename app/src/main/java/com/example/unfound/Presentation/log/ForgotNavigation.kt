package com.example.unfound.Presentation.log

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ForgotDestination

fun NavController.navigateToForgotScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = ForgotDestination,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.ForgotScreen(
    onNavigateBack: () -> Unit
) {
    composable<ForgotDestination> {
        ForgotRoute(
        )
    }
}