package com.example.unfound.signUp

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.unfound.Login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
object  SingUpNavigationDestination

fun NavGraphBuilder.HomeScreen(onLoginClick: () -> Unit) {
    composable(route = "login") { // Debe especificarse una ruta en el composable.
        LoginRoute(onLoginClick = onLoginClick) // Llamada directa a LoginRoute con el parámetro onLoginClick.
    }
}