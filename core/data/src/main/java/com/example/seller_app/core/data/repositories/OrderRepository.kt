package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.order.OrderDetail
import com.example.seller_app.core.model.order.StoreOrders

interface OrderRepository {
    suspend fun getOrders(): Result<StoreOrders>
    suspend fun getOrderById(id: String): Result<OrderDetail>
    suspend fun confirmDeliveredOrder(orderId: String): Result<Unit>
}