package com.example.seller_app.core.network.model.response.product

import com.example.seller_app.core.model.product.ProductItem


data class ProductItemDtoRes(
    val id: String,
    val color: ColorDtoRes?,
    val imageUrl: String,
    val price: Int,
    val size: SizeDtoRes?,
    val stockQuantity: Int
)

fun ProductItemDtoRes.asExternalModel() = ProductItem(
    id,
    color?.asExternalModel(),
    imageUrl,
    price,
    size?.asExternalModel(),
    stockQuantity
)