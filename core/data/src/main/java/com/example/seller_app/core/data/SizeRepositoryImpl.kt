package com.example.seller_app.core.data

import android.util.Log
import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.data.repositories.SizeRepository
import com.example.seller_app.core.database.datasources.SizeLocalDataSource
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.product.Size
import com.example.seller_app.core.network.datasources.SizeRemoteDataSource

class SizeRepositoryImpl(
    private val localDataSource: SizeLocalDataSource,
    private val remoteDataSource: SizeRemoteDataSource
) : SizeRepository {

    override suspend fun getSizesByCategory(categoryId: String): List<Size> {
        return localDataSource.getSizesByCategory(categoryId).map { it.asExternalModel() }
    }


    override suspend fun sync() {
        remoteDataSource.fetchSizes()
            .onSuccess { sizeDtoResList ->
                val newSizes = sizeDtoResList.map { it.asEntity() }
                val newSizesIds = newSizes.map { it.id }

                localDataSource.upsertSizes(newSizes)
                localDataSource.deleteSizesNotIn(newSizesIds)

                Log.d("SizeRepository", "synchronize: Success")
            }
            .onFailure { ex ->
                Log.d("SizeRepository", "synchronize Exception: $ex")
            }
    }
}