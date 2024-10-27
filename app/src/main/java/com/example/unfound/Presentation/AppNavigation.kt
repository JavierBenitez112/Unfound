package com.example.unfound.Presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.example.unfound.Presentation.Login.LoginDestination

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SignMethodsNavGraph,
        modifier = modifier
    ) {
        LoginGraph(navController)
        HomeGraph(navController)
    }
}
