package com.example.seller_app.core.data

import android.util.Log
import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.database.datasources.ColorLocalDataSource
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.product.Color
import com.example.seller_app.core.network.datasources.ColorRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ColorRepositoryImpl(
    private val localDataSource: ColorLocalDataSource,
    private val remoteDataSource: ColorRemoteDataSource
) : ColorRepository {

    override fun getAllColors(): Flow<List<Color> >{
        return localDataSource.getColorsFlow().map { list -> list.map { it.asExternalModel() } }
    }

    override suspend fun sync() {
        remoteDataSource.fetchColors()
            .onSuccess { colorDtoResList ->
                val newColors = colorDtoResList.map { it.asEntity() }
                val newColorsIds = newColors.map { it.id }

                localDataSource.upsertColors(newColors)
                localDataSource.deleteColorsNotIn(newColorsIds)

                Log.d("ColorRepository", "synchronize: Success")
            }
            .onFailure { ex ->
                Log.d("ColorRepository", "synchronize Exception: $ex")
            }
    }
}