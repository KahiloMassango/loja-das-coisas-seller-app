package com.example.seller_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.features.authentication.login.navigation.LoginRoute
import com.example.seller_app.features.home.navigation.HomeRoute
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                appViewModel.showSplashScreen
            }
        }

        enableEdgeToEdge()
        setContent {
            SellerappTheme {
                Surface(
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val navController = rememberNavController()
                    val isLoggedIn by appViewModel.isLoggedInFlow.collectAsState(false)

                    val startDestination: Any = if (isLoggedIn) HomeRoute else LoginRoute
                    App(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}

