package com.example.unfound.Presentation.Login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LoginDestination

fun NavGraphBuilder.LoginScreen1(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    composable<LoginDestination> {
        LoginRoute(
            onLoginClick = onLoginClick,
            onSignUpClick = onSignUpClick
        )
    }
}