package com.example.seller_app.core.data

import android.util.Log
import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.data.repositories.GenderRepository
import com.example.seller_app.core.database.datasources.GenderLocalDataSource
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.Gender
import com.example.seller_app.core.network.datasources.GenderRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenderRepositoryImpl(
    private val localDataSource: GenderLocalDataSource,
    private val remoteDataSource: GenderRemoteDataSource
): GenderRepository {

    override  fun getGenders(): Flow<List<Gender>>{
        return  localDataSource.getGendersFlow().map { list -> list.map { it.asExternalModel() } }
    }


    override suspend fun sync() {
        remoteDataSource.fetchGenders()
            .onSuccess { sizeDtoResList ->
                val newGenders = sizeDtoResList.map { it.asEntity() }
                val newGendersIds = newGenders.map { it.id }

                localDataSource.upsertGenders(newGenders)
                localDataSource.deleteGendersNotIn(newGendersIds)
                Log.d("GenderRepository", "synchronize Success")
            }
            .onFailure { ex ->
                Log.d("GenderRepository", "synchronize Exception: $ex")
            }
    }
}