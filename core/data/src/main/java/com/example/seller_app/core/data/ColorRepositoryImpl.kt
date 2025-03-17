package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.data.repositories.ColorRepository
import com.example.seller_app.core.database.datasources.ColorLocalDataSource
import com.example.seller_app.core.database.model.asExternalModel
import com.example.seller_app.core.model.product.Color
import com.example.seller_app.core.network.model.response.product.ColorDtoRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ColorRepositoryImpl(
    private val localDataSource: ColorLocalDataSource,
) : ColorRepository {

    override fun getAllColors(): Flow<List<Color> >{
        return localDataSource.getColorsFlow().map { list -> list.map { it.asExternalModel() } }
    }

    override suspend fun sync(colors: List<ColorDtoRes>) {
        localDataSource.upsertColors(colors.map { it.asEntity() })
    }
}