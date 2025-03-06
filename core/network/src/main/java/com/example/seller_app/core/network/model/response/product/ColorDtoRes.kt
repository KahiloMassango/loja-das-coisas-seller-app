package com.example.seller_app.core.network.model.response.product

import com.example.seller_app.core.model.product.Color

data class ColorDtoRes(
    val id: String,
    val name: String
)

fun ColorDtoRes.asExternalModel() = Color(
    id = id,
    name = name
)
