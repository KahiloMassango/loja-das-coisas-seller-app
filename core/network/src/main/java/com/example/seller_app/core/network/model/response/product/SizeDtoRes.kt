package com.example.seller_app.core.network.model.response.product

import com.example.seller_app.core.model.product.Size

data class SizeDtoRes(
    val id: String,
    val value: String,
    val categoryId: String,
)

fun SizeDtoRes.asExternalModel() = Size(
    id = id,
    value = value,
    categoryId = categoryId
)