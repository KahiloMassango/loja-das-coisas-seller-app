package com.example.seller_app.core.database

import com.example.seller_app.core.database.dao.SizeDao
import com.example.seller_app.core.database.datasources.SizeLocalDataSource
import com.example.seller_app.core.database.model.SizeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SizeLocalDataSourceImpl(
    private val sizeDao: SizeDao,
) : SizeLocalDataSource {

    override suspend fun upsertSizes(sizes: List<SizeEntity>) {
        withContext(Dispatchers.IO) {
            val newSizeIds = sizes.map { it.id }
            sizeDao.insertSizes(sizes)
            sizeDao.deleteSizesNotIn(newSizeIds)
        }
    }

    override suspend fun getSizesByCategory(categoryId: String): List<SizeEntity> {
        return withContext(Dispatchers.IO) {
            sizeDao.getSizesByCategoryId(categoryId)
        }
    }

    override suspend fun deleteSizesNotIn(sizes: List<String>) {
        withContext(Dispatchers.IO) {
            sizeDao.deleteSizesNotIn(sizes)
        }
    }
}