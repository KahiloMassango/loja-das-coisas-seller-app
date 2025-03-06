package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.product.Size

interface SizeRepository {
    suspend fun getSizesByCategory(categoryId: String): List<Size>
    suspend fun sync()

}