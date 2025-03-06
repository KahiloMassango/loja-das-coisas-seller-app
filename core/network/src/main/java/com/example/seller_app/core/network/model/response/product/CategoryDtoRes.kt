package com.example.seller_app.core.network.model.response.product

import com.example.seller_app.core.model.product.Category

data class CategoryDtoRes(
    val id: String,
    val name: String,
    val hasSizeVariation: Boolean,
    val hasColorVariation: Boolean
)

fun CategoryDtoRes.asExternalModel() = Category(
    id = id,
    name = name,
    hasSizeVariation = hasSizeVariation,
    hasColorVariation = hasColorVariation
)
