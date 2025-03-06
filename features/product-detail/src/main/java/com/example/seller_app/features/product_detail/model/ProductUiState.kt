package com.example.seller_app.features.product_detail.model

import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.Product

internal data class ProductUiState(
    val id: String,
    val productName: String,
    val description: String,
    val image: String?,
    val isAvailable: Boolean,
    val gender: String,
    val category: Category,
)
