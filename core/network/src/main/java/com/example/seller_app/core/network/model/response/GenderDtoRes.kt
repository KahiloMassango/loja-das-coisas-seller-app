package com.example.seller_app.core.network.model.response

import com.example.seller_app.core.model.Gender

data class GenderDtoRes(
    val id: String,
    val name: String,
    val categoryId: String
)

fun GenderDtoRes.asExternalModel() = Gender(
    id = id,
    name = name
)
