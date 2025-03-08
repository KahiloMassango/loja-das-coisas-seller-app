package com.example.seller_app.core.model.product

data class ProductItemRequest(
    val stockQuantity: Int,
    val price: Int,
    val imageUrl: String,
    val size: Size?,
    val color: Color?
)
