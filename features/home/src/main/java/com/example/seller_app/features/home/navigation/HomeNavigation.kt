package com.example.seller_app.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeScreen(
    onOrderDetail: (String) -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(
            onOrderDetail = onOrderDetail
        )
    }
}