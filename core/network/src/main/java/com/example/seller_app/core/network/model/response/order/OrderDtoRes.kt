package com.example.seller_app.core.network.model.response.order

import com.example.seller_app.core.model.order.Order
import com.example.seller_app.core.model.order.OrderDetail
import com.example.seller_app.core.model.order.StoreOrders
import java.util.UUID

data class OrderDtoRes(
    val id: String,
    val customerName: String,
    val date: String,
    val total: Int,
    val orderTotalItems: Int,
    val pending: Boolean
)

fun OrderDtoRes.asExternalModel() = Order(
    id = id,
    customerName = customerName,
    date = date,
    total = total,
    totalItems = orderTotalItems,
    isPending = pending
)

data class OrdersDtoRes(
    val totalPendingOrders: Int,
    val totalDeliveredOrders: Int,
    val delivered: List<OrderDtoRes>,
    val pending: List<OrderDtoRes>,
)

fun OrdersDtoRes.asExternalModel() = StoreOrders(
    totalPendingOrders = totalPendingOrders,
    totalDeliveredOrders = totalDeliveredOrders,
    delivered = delivered.map(OrderDtoRes::asExternalModel),
    pending = pending.map(OrderDtoRes::asExternalModel)
)

data class OrderDetailDtoRes(
    val id: String,
    val customerName: String,
    val customerPhoneNumber: String,
    val date: String,
    val subTotal: Int,
    val deliveryFee: Int,
    val total: Int,
    val deliveryAddressName: String,
    val paymentType: String,
    val deliveryMethod: String,
    val status: String,
    val orderItems: List<OrderItemDtoRes>
)

fun OrderDetailDtoRes.asExternalModel() = OrderDetail(
    id = id,
    storeName = customerName,
    customerPhoneNumber = customerPhoneNumber,
    date = date,
    subTotal = subTotal,
    deliveryFee = deliveryFee,
    total = total,
    deliveryAddressName = deliveryAddressName,
    paymentType = paymentType,
    deliveryMethod = deliveryMethod,
    status = status,
    orderItems = orderItems.map(OrderItemDtoRes::asExternalModel)
)
