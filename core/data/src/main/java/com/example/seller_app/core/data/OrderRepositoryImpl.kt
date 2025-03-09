package com.example.seller_app.core.data

import com.example.seller_app.core.data.repositories.OrderRepository
import com.example.seller_app.core.model.order.OrderDetail
import com.example.seller_app.core.model.order.StoreOrders
import com.example.seller_app.core.network.datasources.OrderNetworkDataSource
import com.example.seller_app.core.network.model.response.order.asExternalModel

class OrderRepositoryImpl(
    private val networkDataSource: OrderNetworkDataSource
): OrderRepository {

    override suspend fun getOrders(): Result<StoreOrders> {
        return networkDataSource.getOrders().mapCatching { it.asExternalModel() }
    }

    override suspend fun getOrderById(id: String): Result<OrderDetail> {
        return networkDataSource.getOrderById(id).mapCatching { it.asExternalModel() }
    }
}