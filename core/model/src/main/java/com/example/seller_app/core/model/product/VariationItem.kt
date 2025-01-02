package com.example.seller_app.core.model.product


data class VariationItem(
    val id: String,
    val color: String?,
    val image: String?,
    val price: Double,
    val size: String?,
    val stockQuantity: Int
)