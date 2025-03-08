package com.example.seller_app.features.add_product.model

import com.example.seller_app.core.model.Gender
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.ProductItem
import com.example.seller_app.core.model.product.ProductItemRequest

internal data class ProductUiState(
    val productName: String = "",
    val description: String = "",
    val image: String = "",
    val gender: Gender? = null,
    val category: Category? = null,
    val isAvailable: Boolean = false,
    val productItems: List<ProductItemRequest> = emptyList()
)

