package com.example.unfound.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.example.unfound.Presentation.Login.LoginDestination
import com.example.unfound.Presentation.Login.LoginScreen1
import com.example.unfound.Presentation.Sign.SignIn.SignInDestination
import com.example.unfound.Presentation.Sign.SignIn.SignInScreen
import com.example.unfound.Presentation.Sign.SignInViewModel
import com.example.unfound.Presentation.log.ForgotScreen
import com.example.unfound.Presentation.log.navigateToForgotScreen
import com.example.unfound.Presentation.Sign.signUp.SignUpDestination
import com.example.unfound.Presentation.Sign.signUp.signUpScreen
import kotlinx.serialization.Serializable

@Serializable
data object SignMethodsNavGraph

fun NavGraphBuilder.LoginGraph(
    navController: NavController,
    signInViewModel: SignInViewModel
) {
    navigation<SignMethodsNavGraph>(
        startDestination = LoginDestination
    ) {
        LoginScreen1(
            onLoginClick = {
                navController.navigate(SignInDestination)
            },
            onSignUpClick = {
                navController.navigate(SignUpDestination)
            }
        )
        SignInScreen(
            onSignInClick = {
                signInViewModel.authenticateUser {
                    navController.NavigateToMapGraph(
                        navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                            inclusive = false
                        ).build()
                    )
                }
            },
            onForgotPasswordClick = navController::navigateToForgotScreen
        )
        ForgotScreen(
            onNavigateBack = navController::navigateUp
        )
        signUpScreen(
            onSignUpClick = {
                signInViewModel.authenticateUser {
                    navController.NavigateToMapGraph(
                        navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                            inclusive = false
                        ).build()
                    )
                }
            }
        )
    }
}