package com.example.seller_app.core.network.model.request


data class ProductItemDtoReq(
    val stockQuantity: Int,
    val price: Int,
    val imageUrl: String,
    val sizeId: String?,
    val colorId: String?
)

data class ProductItemUpdateDtoReq(
    val stockQuantity: Int,
    val price: Int,
    val imageUrl: String
)