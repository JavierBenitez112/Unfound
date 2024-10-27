package com.example.unfound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unfound.Login.LoginRoute
import com.example.unfound.signUp.HomeScreen
import com.example.unfound.signUp.SignUpScreen
import com.example.unfound.ui.theme.UnfoundTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnfoundTheme {
                // Navegación
                val navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginRoute(onLoginClick = {
                // Aquí navegas a la pantalla de registro
                navController.navigate("signUp")
            })
        }
        composable("signUp") {
            SignUpScreen(onSignUpClick = {
                // Aquí navegas a la pantalla de inicio
                navController.navigate("home") {
                    // Opcional: Limpia la pila de navegación
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("home") {
            HomeScreen()
        }
    }
}
