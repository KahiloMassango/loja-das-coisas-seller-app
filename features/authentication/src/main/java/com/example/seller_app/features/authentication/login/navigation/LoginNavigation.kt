package com.example.seller_app.features.authentication.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.authentication.login.LoginScreen
import kotlinx.serialization.Serializable


@Serializable
data object LoginRoute

fun NavGraphBuilder.loginScreen(
    onForgotPassword: () -> Unit,
) {
    composable<LoginRoute> {
        LoginScreen(
            onForgotPassword = onForgotPassword,
        )
    }
}