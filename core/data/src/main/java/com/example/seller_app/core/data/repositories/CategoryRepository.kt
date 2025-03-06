package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.product.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>

    suspend fun sync()

}