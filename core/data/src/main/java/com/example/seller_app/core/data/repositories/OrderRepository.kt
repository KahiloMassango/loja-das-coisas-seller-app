package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.order.Order
import com.example.seller_app.core.model.order.OrderDetail
import com.example.seller_app.core.model.order.StoreOrders
import com.example.seller_app.core.network.model.response.order.OrderDetailDtoRes
import com.example.seller_app.core.network.model.response.order.OrdersDtoRes

interface OrderRepository {
    suspend fun getOrders(): Result<StoreOrders>
    suspend fun getOrderById(id: String): Result<OrderDetail>
}