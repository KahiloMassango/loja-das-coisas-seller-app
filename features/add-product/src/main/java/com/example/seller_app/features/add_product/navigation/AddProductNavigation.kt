package com.example.seller_app.features.add_product.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.add_product.AddProductScreen
import kotlinx.serialization.Serializable

@Serializable
data object AddProductRoute

fun NavController.navigateToAddProduct() = navigate(AddProductRoute)

fun NavGraphBuilder.addProductScreen(
    onNavigateUp: () -> Unit
) {
    composable<AddProductRoute> {
        AddProductScreen(
            onNavigateUp = onNavigateUp

        )
    }
}