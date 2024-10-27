package com.example.unfound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.unfound.Home.HomeScreen
import com.example.unfound.Map.MapScreen1
import com.example.unfound.SignIn.SignInScreen
import com.example.unfound.loading.LoadingScreen
import com.example.unfound.profile.ProfileScreen
import com.example.unfound.ui.theme.UnfoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnfoundTheme {
                ProfileScreen()
            }
        }
    }
}

