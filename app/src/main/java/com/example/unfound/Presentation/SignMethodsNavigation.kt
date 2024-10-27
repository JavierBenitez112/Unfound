package com.example.unfound.Presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.example.unfound.Presentation.Login.LoginDestination
import com.example.unfound.Presentation.Login.LoginScreen1
import com.example.unfound.Presentation.SignIn.SignInDestination
import com.example.unfound.Presentation.SignIn.SignInScreen
import com.example.unfound.Presentation.log.ForgotScreen
import com.example.unfound.Presentation.log.navigateToForgotScreen
import com.example.unfound.Presentation.signUp.SignUpDestination
import com.example.unfound.Presentation.signUp.signUpScreen
import kotlinx.serialization.Serializable

@Serializable
data object SignMethodsNavGraph

fun NavController.navigateLoginGraph(navOptions: NavOptions? = null) {
    this.navigate(SignMethodsNavGraph, navOptions)
}

fun NavGraphBuilder.LoginGraph(
    navController: NavController
){
    navigation<SignMethodsNavGraph>(
        startDestination = LoginDestination
    ){
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
                navController.NavigateToMapGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                        inclusive = true
                    ).build()
                )
            },
            onForgotPasswordClick = navController::navigateToForgotScreen
        )
        ForgotScreen(
            onNavigateBack = navController::navigateUp
        )
        signUpScreen(
            onSignUpClick = {
                navController.NavigateToMapGraph(
                    navOptions = NavOptions.Builder().setPopUpTo<LoginDestination>(
                        inclusive = true
                    ).build()
                )
            }
        )
    }
}