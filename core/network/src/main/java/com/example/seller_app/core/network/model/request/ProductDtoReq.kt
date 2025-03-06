package com.example.seller_app.core.network.model.request

data class ProductDtoReq(
    val name: String,
    val isAvailable: Boolean,
    val imageUrl: String,
    val categoryId: String,
    val genderId: String,
    val description: String,
    val productItems: List<ProductItemDtoReq>
)

data class ProductUpdateDtoReq(
    val name: String,
    val isAvailable: Boolean,
    val description: String,
    val imageUrl: String,
)