package com.example.seller_app.core.data

import com.example.seller_app.core.model.ProductSize
import kotlinx.coroutines.flow.Flow

interface SizeRepository {
    suspend fun getSizesBySubcategoryId(subcategory: String): List<String>
    suspend fun addSize(size: ProductSize)

}