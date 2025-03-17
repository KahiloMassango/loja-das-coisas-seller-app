package com.example.seller_app.core.database

import com.example.seller_app.core.database.dao.CategoryDao
import com.example.seller_app.core.database.dao.GenderCategoryDao
import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.seller_app.core.database.model.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CategoryLocalDataSourceImp(
    private val categoryDao: CategoryDao,
    private val genderCategoryDao: GenderCategoryDao
) : CategoryLocalDataSource {

    override suspend fun upsertCategories(categories: List<CategoryEntity>) {
        withContext(Dispatchers.IO) {
            val newCategoriesIds = categories.map { it.id }
            categoryDao.insertCategories(categories)
            categoryDao.deleteCategoriesNotIn(newCategoriesIds)
        }
    }

    override fun getCategoryFlow(): Flow<List<CategoryEntity>> {
        return categoryDao.getCategoryFlow()
    }

    override fun getCategoriesByGenderFlow(genderId: String): Flow<List<CategoryEntity>> {
        return categoryDao.getCategoriesByGenderFlow(genderId)
    }

    override fun getCategoriesByGenderName(name: String): Flow<List<CategoryEntity>> {
        return categoryDao.getCategoriesByGenderName(name).map { it }

    }



}