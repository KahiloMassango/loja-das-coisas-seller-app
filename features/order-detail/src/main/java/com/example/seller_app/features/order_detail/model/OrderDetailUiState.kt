package com.example.seller_app.features.order_detail.model

import com.example.seller_app.core.model.order.OrderDetail

internal sealed class OrderDetailUiState {
    data object Loading : OrderDetailUiState()
    data object Error : OrderDetailUiState()
    data class Success(val order: OrderDetail) : OrderDetailUiState()
}
