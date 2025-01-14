package com.example.unfound

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.unfound.Presentation.loading.ErrorScreen
import com.example.unfound.ui.theme.UnfoundTheme
import com.google.android.libraries.places.api.Places
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.unfound.Data.local.DataStoreUserPrefs
import com.example.unfound.Presentation.Sign.SignInViewModel
import com.example.unfound.Presentation.Sign.SignStatus
import com.example.unfound.navigation.AppNavigation

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    private val authViewModel: SignInViewModel by viewModels { SignInViewModel.Factory }
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private var keepSplashScreenOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreenOn && authViewModel.authStatus.value is SignStatus.Loading
        }

        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            initializeMap()
        }
    }

    private fun initializeMap() {
        val apiKey = BuildConfig.API_KEY

        if (apiKey.isEmpty() || apiKey == "DEFAULT_API_KEY") {
            Log.e("Places test", "No API key")
            finish()
            return
        }

        Places.initializeWithNewPlacesApiEnabled(applicationContext, apiKey)

        setContent {
            UnfoundTheme {
                Surface {
                    AppNavigation(viewModel = authViewModel,dataStoreUserPrefs = DataStoreUserPrefs(dataStore))
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                initializeMap()
            } else {
                keepSplashScreenOn = false
                setContent {
                    UnfoundTheme {
                        Surface {
                            ErrorScreen(onRetry = {
                                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
                            })
                        }
                    }
                }
            }
        }
    }
}