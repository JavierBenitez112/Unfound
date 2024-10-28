package com.example.unfound.Presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.example.unfound.Presentation.Map.MapDestination
import com.example.unfound.Presentation.Map.MapScreen
import com.example.unfound.Presentation.profile.ProfileScreen
import com.example.unfound.Presentation.profile.navigateToProfileScreen
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
        MapScreen(
            onProfileClick = {
                navController.navigateToProfileScreen()
            }
        )
        ProfileScreen(
            onBackClick = navController::navigateUp
        )
    }
}