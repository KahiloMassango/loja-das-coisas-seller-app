package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.model.ProductSize

class DefaultSizeRepository(
    private val sizeDao: SizeDao

): SizeRepository {

    override suspend fun getSizesBySubcategoryId(subcategory: String): List<String> {
        return sizeDao.getAllSizes(subcategory).map { it.value }
    }

    override suspend fun addSize(size: ProductSize) {
        sizeDao.addSize(size.asEntity())
    }
}