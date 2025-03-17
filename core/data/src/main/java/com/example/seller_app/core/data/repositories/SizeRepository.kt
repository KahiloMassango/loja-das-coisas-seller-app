package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.product.Size
import com.example.seller_app.core.network.model.response.product.SizeDtoRes

interface SizeRepository {
    suspend fun getSizesByCategory(categoryId: String): List<Size>

    suspend fun sync(sizes: List<SizeDtoRes>)
}