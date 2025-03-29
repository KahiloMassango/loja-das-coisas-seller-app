package com.example.seller_app.features.order_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.seller_app.features.order_detail.OrderDetailScreen
import kotlinx.serialization.Serializable


@Serializable
data class OrderDetailRoute(val id: String)

fun NavController.navigateToOrderDetail(orderId: String) = navigate(OrderDetailRoute(orderId))

fun NavGraphBuilder.orderDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable<OrderDetailRoute>() {
        OrderDetailScreen(
            onNavigateUp = onNavigateUp
        )

    }
}