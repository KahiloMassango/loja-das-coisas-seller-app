package com.example.seller_app.core.network.datasources

import com.example.seller_app.core.network.model.response.order.OrderDetailDtoRes
import com.example.seller_app.core.network.model.response.order.OrdersDtoRes

interface OrderNetworkDataSource {
    suspend fun getOrders(): Result<OrdersDtoRes>
    suspend fun getOrderById(id: String): Result<OrderDetailDtoRes>
}