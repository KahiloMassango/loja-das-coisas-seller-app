package com.example.seller_app.core.data

import com.example.seller_app.core.model.Subcategory
import kotlinx.coroutines.flow.Flow

interface SubCategoryRepository {
    suspend fun getSubcategoriesByCategoryId(categoryId: String): List<String>
    fun addSubCategory(subCategory: Subcategory)

}