package com.example.unfound.Presentation.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.unfound.Data.local.DataStoreUserPrefs
import com.example.unfound.Presentation.log.ForgotRoute
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavController.navigateToProfileScreen(
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = ProfileDestination,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.ProfileScreen(
    onBackClick: () -> Unit,
    dataStoreUserPrefs: DataStoreUserPrefs
) {
    composable<ProfileDestination> {
        ProfileRoute(
            onBackClick = onBackClick,
            dataStoreUserPrefs = dataStoreUserPrefs)

    }
}