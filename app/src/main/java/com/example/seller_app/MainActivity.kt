package com.example.seller_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.seller_app.core.data.repositories.AccountRepository
import com.example.seller_app.core.ui.theme.SellerappTheme
import com.example.seller_app.features.authentication.login.navigation.LoginRoute
import com.example.seller_app.features.home.navigation.HomeRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val appViewModel: AppViewModel by viewModels()

    @Inject
    lateinit var accountRepository: AccountRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            appViewModel.showSplashScreen
        }

        enableEdgeToEdge()
        setContent {
            SellerappTheme {
                val navController = rememberNavController()
                val isLoggedIn by appViewModel.isLoggedInFlow.collectAsState()

                LaunchedEffect(isLoggedIn) {
                    if (isLoggedIn) {
                        navController.navigate(HomeRoute) {
                            popUpTo(0)  // Clears the back stack.
                        }
                    } else {
                        navController.navigate(LoginRoute) {
                            popUpTo(0)
                        }
                    }
                }

                Surface(
                    color = MaterialTheme.colorScheme.surface
                ) {

                    App(
                        navController = navController,
                        startDestination = LoginRoute
                    )
                }
            }
        }
    }
}



