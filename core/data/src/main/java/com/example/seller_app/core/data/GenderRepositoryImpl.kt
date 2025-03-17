package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.database.datasources.GenderLocalDataSource
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.Gender
import com.example.seller_app.core.network.model.response.GenderCategoryDtoRes
import com.example.seller_app.core.network.model.response.GenderDtoRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenderRepositoryImpl(
    private val localDataSource: GenderLocalDataSource,
): GenderRepository {

    override  fun getGenders(): Flow<List<Gender>>{
        return  localDataSource.getGendersFlow().map { list -> list.map { it.asExternalModel() } }

    }

    override suspend fun sync(genders: List<GenderDtoRes>) {
        localDataSource.upsertGenders(genders.map { it.asEntity() })
    }

    override suspend fun syncGenderCategories(genderCategories: List<GenderCategoryDtoRes>) {
        localDataSource.saveGenderCategories(genderCategories.map { it.asEntity() })
    }

}