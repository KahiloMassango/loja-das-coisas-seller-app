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
import com.example.seller_app.features.authentication.login.navigation.LoginRoute
import com.example.seller_app.features.authentication.login.navigation.loginScreen
import com.example.seller_app.features.finances.navigation.financeScreen
import com.example.seller_app.features.home.navigation.HomeRoute
import com.example.seller_app.features.home.navigation.homeScreen
import com.example.seller_app.features.order_detail.navigation.OrderDetailRoute
import com.example.seller_app.features.order_detail.navigation.navigateToOrderDetail
import com.example.seller_app.features.order_detail.navigation.orderDetailScreen
import com.example.seller_app.features.product_detail.navigation.navigateToProductDetail
import com.example.seller_app.features.product_detail.navigation.productDetailScreen
import com.example.seller_app.features.products.navigation.productsScreen
import com.example.seller_app.features.product_items.navigation.navigateToProductItems
import com.example.seller_app.features.product_items.navigation.productItemsScreen
import com.example.seller_app.navigation.AppNavigationBar


@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Any
) {
    Column(
        modifier = modifier.fillMaxSize()
    ){
        NavHost(
            modifier = Modifier.weight(1f),
            navController = navController,
            startDestination = startDestination
        ) {
            loginScreen(
                onLogin = {},
                onSignUp = {},
                onForgotPassword = {}
            )

            homeScreen(
                onOrderDetail = { id -> navController.navigateToOrderDetail(id) }
            )

            orderDetailScreen(
                onNavigateUp = navController::navigateUp
            )

            addProductScreen(
                onNavigateUp = navController::navigateUp
            )

            productDetailScreen(
                onNavigateUp = navController::navigateUp,
                onVariationsClick = { navController.navigateToProductItems(it) }
            )

            productsScreen(
                onAddNewProduct = { navController.navigateToAddProduct() },
                onProductClick = { id -> navController.navigateToProductDetail(id) }
            )

            productItemsScreen(onNavigateUp = navController::navigateUp)

            financeScreen()
        }
        AppNavigationBar(
            navController = navController
        )
    }
}