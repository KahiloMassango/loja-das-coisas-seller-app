package com.example.seller_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.seller_app.features.add_product.navigation.addProductScreen
import com.example.seller_app.features.add_product.navigation.navigateToAddProduct
import com.example.seller_app.features.home.navigation.HomeRoute
import com.example.seller_app.features.home.navigation.homeScreen
import com.example.seller_app.features.product_detail.navigation.navigateToProductDetail
import com.example.seller_app.features.product_detail.navigation.productDetailScreen
import com.example.seller_app.features.products.navigation.productsScreen
import com.example.seller_app.navigation.AppNavigationBar


@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        NavHost(
            modifier = Modifier.weight(1f),
            navController = navController,
            startDestination = HomeRoute
        ) {
            homeScreen()
            addProductScreen(
                onNavigateUp = navController::navigateUp
            )
            productDetailScreen(
                onNavigateUp = navController::navigateUp
            )
            productsScreen(
                onAddNewProduct = { navController.navigateToAddProduct() },
                onProductClick = { id -> navController.navigateToProductDetail(id) }
            )
        }
        AppNavigationBar(
            navController = navController
        )
    }
}