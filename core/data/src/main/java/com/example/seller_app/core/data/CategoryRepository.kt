package com.example.seller_app.core.data

import com.example.seller_app.core.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategoriesStream(): Flow<List<String>>
    fun addCategory(category: Category)

}