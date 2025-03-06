package com.example.seller_app.core.model

import com.example.seller_app.core.model.product.ProductItem

data class Order(
    val id: String,
    val customerName: String,
    val customerPhone: String,
    val orderDate: String,
    val orderStatus: String,
    val orderTotal: Double,
    val orderItems: List<ProductItem>
)
