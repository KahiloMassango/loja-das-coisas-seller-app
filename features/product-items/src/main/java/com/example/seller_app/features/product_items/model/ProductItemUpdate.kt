package com.example.seller_app.features.product_items.model

internal data class ProductItemUpdate(
    val productItemId: String,
    val stockQuantity: Int,
    val price: Int,
    val image: String?
)