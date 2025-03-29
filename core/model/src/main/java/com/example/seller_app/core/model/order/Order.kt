package com.example.seller_app.core.model.order

data class Order(
    val id: String,
    val customerName: String,
    val date: String,
    val isPending: Boolean,
    val total: Int,
    val totalItems: Int,
)

data class StoreOrders(
    val totalPendingOrders: Int,
    val totalDeliveredOrders: Int,
    val delivered: List<Order>,
    val pending: List<Order>,
)


data class OrderDetail(
    val id: String,
    val customerName: String,
    val customerPhoneNumber: String,
    val date: String,
    val subTotal: Int,
    val deliveryFee: Int,
    val total: Int,
    val deliveryAddressName: String,
    val paymentType: String,
    val delivered: Boolean,
    val orderItems: List<OrderItem>
)
