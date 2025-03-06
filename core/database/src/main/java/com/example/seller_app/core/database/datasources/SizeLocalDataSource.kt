package com.example.seller_app.core.database.datasources

import com.example.seller_app.core.database.model.SizeEntity
import kotlinx.coroutines.flow.Flow

interface SizeLocalDataSource {

    suspend fun getSizesByCategory(categoryId: String): List<SizeEntity>

    suspend fun upsertSizes(sizes: List<SizeEntity>)

    suspend fun deleteSizesNotIn(sizes: List<String>)

}