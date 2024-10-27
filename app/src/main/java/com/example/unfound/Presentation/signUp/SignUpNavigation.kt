package com.example.unfound.Presentation.signUp

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SignUpDestination

fun NavGraphBuilder.signUpScreen(
    onSignUpClick: () -> Unit
) {
    composable<SignUpDestination> {
        SignUpRoute(
            onSignUpClick = onSignUpClick
        )
    }
}