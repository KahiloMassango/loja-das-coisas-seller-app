package com.example.seller_app.core.model.product

data class Order(
    val id: String,
    val customerName: String,
    val customerPhone: String,
    val orderDate: String,
    val orderStatus: String,
    val orderTotal: Double,
    val orderItems: List<VariationItem>
)
