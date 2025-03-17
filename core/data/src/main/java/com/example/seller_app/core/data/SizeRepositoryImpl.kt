package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.core.database.datasources.SizeLocalDataSource
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.product.Size
import com.example.seller_app.core.network.model.response.product.SizeDtoRes

class SizeRepositoryImpl(
    private val localDataSource: SizeLocalDataSource,
) : SizeRepository {

    override suspend fun getSizesByCategory(categoryId: String): List<Size> {
        return localDataSource.getSizesByCategory(categoryId).map { it.asExternalModel() }
    }

    override suspend fun sync(sizes: List<SizeDtoRes>) {
        localDataSource.upsertSizes(sizes.map { it.asEntity() })
    }

}