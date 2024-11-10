package com.example.unfound.Presentation.SignIn

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SignInDestination

fun NavGraphBuilder.SignInScreen(
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
) {
    composable<SignInDestination> {
        SignInRoute(
            onSignInClick = onSignInClick,
            onForgotPasswordClick = onForgotPasswordClick
        )
    }
}
