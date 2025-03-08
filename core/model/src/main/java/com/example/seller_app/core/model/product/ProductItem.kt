package com.example.seller_app.core.model.product


data class ProductItem(
    val id: String,
    val color: Color?,
    val imageUrl: String,
    val price: Int,
    val size: Size?,
    val stockQuantity: Int
)

