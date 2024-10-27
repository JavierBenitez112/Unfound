package com.example.unfound.Presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.example.unfound.Presentation.Login.LoginDestination
import com.example.unfound.Presentation.Map.MapDestination
import com.example.unfound.Presentation.Map.MapScreen
import com.example.unfound.Presentation.SignIn.SignInScreen
import com.example.unfound.Presentation.profile.profileScreen
import com.example.unfound.Presentation.signUp.signUpScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeNavGraph

fun NavController.NavigateToMapGraph(
    navOptions: NavOptions? = null
) {
    this.navigate(HomeNavGraph, navOptions)
}

fun NavGraphBuilder.HomeGraph(
    navController: NavController
){
    navigation<HomeNavGraph>(
        startDestination = MapDestination
    ){
        MapScreen()
        profileScreen(
            onBackClick = navController::navigateUp
        )
    }
}