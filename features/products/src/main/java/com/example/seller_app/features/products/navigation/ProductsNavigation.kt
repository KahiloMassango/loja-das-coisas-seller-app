package com.example.seller_app.features.products.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.products.ProductsScreen
import kotlinx.serialization.Serializable

@Serializable
data object ProductsRoute

fun NavGraphBuilder.productsScreen(
    onAddNewProduct: () -> Unit,
    onProductClick: (String) -> Unit
) {
    composable<ProductsRoute> {
        ProductsScreen(
            onAddNewProduct = onAddNewProduct,
            onProductClick = onProductClick
        )
    }
}