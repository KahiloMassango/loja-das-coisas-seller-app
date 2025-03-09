package com.example.seller_app.core.network.model.response.order

import com.example.seller_app.core.model.order.OrderItem

data class OrderItemDtoRes(
    val image: String,
    val color: String?,
    val size: String?,
    val quantity: Int,
    val price: Int,
)

fun OrderItemDtoRes.asExternalModel() = OrderItem(
    image = image,
    color = color,
    size = size,
    quantity = quantity,
    price = price
)
