package com.example.seller_app.core.network.model.response

data class Response<T>(
    val isSuccess: Boolean,
    val statusCode: Int,
    val message: String,
    val data: T,
)