package com.example.unfound.Presentation.Sign.signUp

import androidx.compose.animation.AnimatedContentScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.unfound.Presentation.signUp.SignUpRoute
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
