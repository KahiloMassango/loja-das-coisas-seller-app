package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultCategoryRepository(
    private val categoryDao: CategoryDao
): CategoryRepository {

    override fun getCategoriesStream(): Flow<List<String>> {
        return categoryDao.getAllCategories().map { list -> list.map { it.name }  }
    }

    override fun addCategory(category: Category) {
        categoryDao.addCategory(category.asEntity())
    }
}