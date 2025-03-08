package com.example.seller_app.core.network.model

data class Response<T>(
    val isSuccess: Boolean,
    val message: String,
    val data: T,
)