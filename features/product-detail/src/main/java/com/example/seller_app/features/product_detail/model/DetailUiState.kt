package com.example.seller_app.features.product_detail.model

internal sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Success(val product: ProductUiState) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}
