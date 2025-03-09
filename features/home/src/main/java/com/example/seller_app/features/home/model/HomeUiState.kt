package com.example.seller_app.features.home.model

import com.example.seller_app.core.model.order.Order

internal sealed class HomeUiState {
    data object Loading: HomeUiState()
    data class Error(val message: String): HomeUiState()
    data class Success(
        val totalPendingOrders: Int,
        val totalDeliveredOrders: Int,
        val pendingOrders: List<Order>,
        val deliveredOrders: List<Order>
    ): HomeUiState()
}