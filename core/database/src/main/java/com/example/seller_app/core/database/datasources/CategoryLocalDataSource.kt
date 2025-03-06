package com.example.seller_app.core.database.datasources

import com.example.seller_app.core.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {

    suspend fun upsertCategories(categories: List<CategoryEntity>)

    suspend fun deleteCategoriesNotIn(categories: List<String>)

    fun getCategoryFlow(): Flow<List<CategoryEntity>>

}