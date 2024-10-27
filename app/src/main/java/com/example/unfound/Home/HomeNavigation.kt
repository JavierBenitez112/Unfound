package com.example.unfound.Home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.unfound.Login.LoginRoute

@Serializable
private data object HomeNavigationDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
) {
    composable<HomeNavigationDestination> {
        LoginRoute(
            onLoginClick = onLoginClick,
        )
    }
}