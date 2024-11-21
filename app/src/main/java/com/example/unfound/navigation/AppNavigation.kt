package com.example.unfound.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.example.unfound.Presentation.Login.LoginDestination
import com.example.unfound.Presentation.Sign.SignInViewModel
import com.example.unfound.Presentation.Sign.SignStatus
import com.example.unfound.Presentation.loading.LoadingScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: SignInViewModel = viewModel(factory = SignInViewModel.Factory)
) {
    val authStatus by viewModel.authStatus.collectAsStateWithLifecycle()

    val startDestination = when (authStatus) {
        SignStatus.Authenticated -> HomeNavGraph
        else -> SignMethodsNavGraph
    }

    LaunchedEffect(authStatus) {
        when (authStatus) {
            SignStatus.Authenticated -> {
                navController.navigate(HomeNavGraph) {
                    popUpTo(SignMethodsNavGraph) {
                        inclusive = true
                    }
                }
            }
            SignStatus.NonAuthenticated -> {
                navController.navigate(SignMethodsNavGraph) {
                    popUpTo(0)
                }
            }
            SignStatus.Loading -> {}
        }
    }

    if (authStatus == SignStatus.Loading) {
        LoadingScreen()
    } else {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            LoginGraph(navController, viewModel)
            HomeGraph(navController)
        }
    }
}