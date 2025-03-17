package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.data.repositories.CategoryRepository
import com.example.seller_app.core.database.datasources.CategoryLocalDataSource
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.product.Category
import com.example.seller_app.core.network.model.response.product.CategoryDtoRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val localDataSource: CategoryLocalDataSource,
) : CategoryRepository {

    override fun getCategories(): Flow<List<Category>> {
        return localDataSource.getCategoryFlow()
            .map { list -> list.map { it.asExternalModel() } }
    }

    override fun getCategoriesByGenderId(genderId: String): Flow<List<Category>> {
        return localDataSource.getCategoriesByGenderFlow(genderId)
            .map { list -> list.map { it.asExternalModel() } }
    }

    override fun getCategoriesByGenderName(name: String): Flow<List<Category>> {
        return localDataSource.getCategoriesByGenderName(name)
            .map { list -> list.map { it.asExternalModel() } }
    }


    override suspend fun sync(categories: List<CategoryDtoRes>) {
        localDataSource.upsertCategories(categories.map { it.asEntity() })
    }

}