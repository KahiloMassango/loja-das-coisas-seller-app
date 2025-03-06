package com.example.seller_app.core.network.model.response.product

import com.example.seller_app.core.model.product.Product
import com.example.seller_app.core.model.product.ProductWithVariation
import com.example.seller_app.core.network.model.response.GenderDtoRes
import com.example.seller_app.core.network.model.response.asExternalModel


data class ProductDtoRes(
    val id: String,
    val name: String,
    val description: String,
    val isAvailable: Boolean,
    val imageUrl: String
)

fun ProductDtoRes.asExternalModel() = Product(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    isAvailable = isAvailable
)

data class ProductWithVariationRes(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isAvailable: Boolean,
    val category: CategoryDtoRes,
    val gender: GenderDtoRes,
    val productItems: List<ProductItemDtoRes>
)

fun ProductWithVariationRes.asExternalModel() = ProductWithVariation(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    isAvailable = isAvailable,
    category = category.asExternalModel(),
    gender = gender.asExternalModel(),
    productItems = productItems.map { it.asExternalModel() }
)

