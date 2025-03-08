package com.example.seller_app.features.product_items.model

import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.model.product.ProductItem

internal sealed class ProductItemsUiState {
    data object Loading : ProductItemsUiState()
    data class Error(val message: String) : ProductItemsUiState()
    data class Success(
        val productName: String,
        val category: Category,
        val productItems: List<ProductItem>) : ProductItemsUiState()

}