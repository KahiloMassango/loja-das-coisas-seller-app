package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.model.ProductSize

class DefaultSizeRepository(
    private val sizeDao: SizeDao

): SizeRepository {

    override suspend fun getSizesByCategory(category: String): List<String> {
        return sizeDao.getAllSizes(category).map { it.value }
    }

    override suspend fun addSize(size: ProductSize) {
        sizeDao.addSize(size.asEntity())
    }
}