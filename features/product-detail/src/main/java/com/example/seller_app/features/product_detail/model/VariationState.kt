package com.example.seller_app.features.product_detail.model

data class VariationState(
    val image: String? = null,
    val color: String? = null,
    val size: String? = null,
    val price: Double = 0.0,
    val quantity: Int = 0
)