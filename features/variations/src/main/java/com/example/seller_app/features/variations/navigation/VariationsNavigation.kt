package com.example.seller_app.features.variations.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.variations.VariationsScreen
import kotlinx.serialization.Serializable

@Serializable
data class VariationsRoute(val productId: String)

fun NavController.navigateToVariations(productId: String) = navigate(VariationsRoute(productId))


fun NavGraphBuilder.variationsScreen(
    onNavigateUp: () -> Unit
) {
    composable<VariationsRoute> {
        VariationsScreen(
            onNavigateUp = onNavigateUp
        )
    }
}
