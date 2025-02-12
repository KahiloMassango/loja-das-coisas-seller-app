package com.example.seller_app.core.data

import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.model.CategoryEntity

class DefaultCategoryRepository(
    private val categoryDao: CategoryDao
): CategoryRepository {
    override suspend fun getAllCategories(): List<String> {
        return categoryDao.getAllCategories().map { it.name }
    }

    override fun addCategory(category: String) {
        categoryDao.addCategory(CategoryEntity(name = category))
    }
}