package com.example.seller_app.core.data

import android.util.Log
import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.network.datasources.CategoryRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val localDataSource: CategoryLocalDataSource,
    private val remoteDataSource: CategoryRemoteDataSource
): CategoryRepository {

    override fun getCategories(): Flow<List<Category>> {
        return localDataSource.getCategoryFlow().map { list -> list.map { it.asExternalModel() } }
    }

    override suspend fun sync() {
        remoteDataSource.fetchCategories()
            .onSuccess { categoryDtoResList ->
                val newCategories = categoryDtoResList.map { it.asEntity() }
                val newCategoriesIds = newCategories.map { it.id }

                localDataSource.upsertCategories(newCategories)
                localDataSource.deleteCategoriesNotIn(newCategoriesIds)
                Log.d("CategoryRepository", "synchronize Success")
            }
            .onFailure { ex ->
                Log.d("CategoryRepository", "synchronize Exception: $ex")
            }

    }
}