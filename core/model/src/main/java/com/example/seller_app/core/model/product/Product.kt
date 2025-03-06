package com.example.seller_app.core.model.product

import com.example.seller_app.core.model.Gender


data class Product(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isAvailable: Boolean
)

data class ProductWithVariation(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isAvailable: Boolean,
    val category: Category,
    val gender: Gender,
    val productItems: List<ProductItem>
)

