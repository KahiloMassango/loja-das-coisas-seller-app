package com.example.seller_app.features.variations.model

import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.ProductItem

internal sealed class VariationsUiState {
    data object Loading : VariationsUiState()
    data class Error(val message: String) : VariationsUiState()
    data class Success(
        val productName: String,
        val category: Category,
        val productItems: List<ProductItem>) : VariationsUiState()

}