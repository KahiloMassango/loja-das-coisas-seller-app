package com.example.seller_app.core.database

import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.seller_app.core.database.model.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CategoryLocalDataSourceImp(
    private val categoryDao: CategoryDao,
) : CategoryLocalDataSource {

    override suspend fun upsertCategories(categories: List<CategoryEntity>) {
        withContext(Dispatchers.IO) {
            categoryDao.insertCategories(categories)
        }
    }

    override fun getCategoryFlow(): Flow<List<CategoryEntity>> {
        return categoryDao.getCategoryFlow()
    }

    override suspend fun deleteCategoriesNotIn(categories: List<String>) {
        withContext(Dispatchers.IO) {
            categoryDao.deleteCategoriesNotIn(categories)
        }
    }

}