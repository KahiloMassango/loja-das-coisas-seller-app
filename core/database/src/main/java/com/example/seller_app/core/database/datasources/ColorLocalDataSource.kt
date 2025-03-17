package com.example.seller_app.core.database.datasources

import com.example.seller_app.core.database.model.ColorEntity
import kotlinx.coroutines.flow.Flow

interface ColorLocalDataSource {

    suspend fun upsertColors(colors: List<ColorEntity>)

    fun getColorsFlow(): Flow<List<ColorEntity>>
}