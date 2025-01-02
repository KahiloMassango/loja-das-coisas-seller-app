package com.example.seller_app.features.product_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.product_detail.ProductDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailRoute(val id: String)

fun NavController.navigateToProductDetail(id: String) = navigate(ProductDetailRoute(id))

fun NavGraphBuilder.productDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable<ProductDetailRoute> {
        ProductDetailScreen(
            onNavigateUp = onNavigateUp
        )

    }
}