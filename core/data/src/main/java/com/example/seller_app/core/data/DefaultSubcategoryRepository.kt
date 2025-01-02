package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.database.dao.SubcategoryDao
import com.example.seller_app.core.model.Subcategory

class DefaultSubcategoryRepository(
    private val subcategoryDao: SubcategoryDao
): SubCategoryRepository {
    override suspend fun getSubcategoriesByCategoryId(categoryId: String): List<String> {
        return subcategoryDao.getAllSubcategories(categoryId).map { it.name }
    }

    override fun addSubCategory(subCategory: Subcategory) {
        subcategoryDao.addSubcategory(subCategory.asEntity())
    }
}