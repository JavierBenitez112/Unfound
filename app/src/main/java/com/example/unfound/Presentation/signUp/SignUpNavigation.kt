package com.example.unfound.Presentation.signUp

import androidx.compose.animation.AnimatedContentScope
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

private fun AnimatedContentScope.SignUpRoute(onSignUpClick: () -> Unit) {

}
