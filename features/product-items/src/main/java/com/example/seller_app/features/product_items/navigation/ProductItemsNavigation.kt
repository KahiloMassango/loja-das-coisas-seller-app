package com.example.seller_app.features.product_items.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.product_items.ProductItemsScreen
import kotlinx.serialization.Serializable

@Serializable
data class ProductItemsRoute(val productId: String)

fun NavController.navigateToProductItems(productId: String) = navigate(ProductItemsRoute(productId))


fun NavGraphBuilder.productItemsScreen(
    onNavigateUp: () -> Unit
) {
    composable<ProductItemsRoute> {
        ProductItemsScreen(
            onNavigateUp = onNavigateUp
        )
    }
}
