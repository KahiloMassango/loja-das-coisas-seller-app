package com.example.seller_app.core.data

import com.example.seller_app.core.model.ProductSize

interface SizeRepository {
    suspend fun getSizesByCategory(category: String): List<String>
    suspend fun addSize(size: ProductSize)

}