package com.example.seller_app.core.model.product

data class Category(
    val id: String,
    val name: String,
    val hasSizeVariation: Boolean,
    val hasColorVariation: Boolean
)
