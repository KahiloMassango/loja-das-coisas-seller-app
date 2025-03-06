package com.example.seller_app.core.database.datasources

import com.example.seller_app.core.database.model.ColorEntity
import kotlinx.coroutines.flow.Flow

interface ColorLocalDataSource {

    suspend fun upsertColors(colors: List<ColorEntity>)

    suspend fun deleteColorsNotIn(colors: List<String>)

    fun getColorsFlow(): Flow<List<ColorEntity>>
}