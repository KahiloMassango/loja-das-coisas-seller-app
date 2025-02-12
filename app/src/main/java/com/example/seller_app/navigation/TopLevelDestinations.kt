package com.example.seller_app.navigation

import androidx.annotation.DrawableRes
import com.example.seller_app.R
import com.example.seller_app.features.finances.navigation.FinanceRoute
import com.example.seller_app.features.home.navigation.HomeRoute
import com.example.seller_app.features.products.navigation.ProductsRoute

enum class TopLevelDestination(
    val title: String,
    val route: Any,
    @DrawableRes
    val icon: Int,
) {
    HOME("Home", HomeRoute, R.drawable.ic_home),
    PRODUCTS("Products", ProductsRoute, R.drawable.ic_products),
    FINANCE("Finance", FinanceRoute, R.drawable.ic_finance),
    STORE("Store", "ProfileRoute", R.drawable.ic_storefront)
}
val topLevelRoutes = TopLevelDestination.entries.map { it.route::class }.toSet()
