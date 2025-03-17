package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.network.model.response.product.CategoryDtoRes
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>

    fun getCategoriesByGenderId(genderId: String): Flow<List<Category>>

    fun getCategoriesByGenderName(name: String): Flow<List<Category>>

    suspend fun sync(categories: List<CategoryDtoRes>)

}