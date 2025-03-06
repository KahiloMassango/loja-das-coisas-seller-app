package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.product.Color
import kotlinx.coroutines.flow.Flow

interface ColorRepository {

    fun getAllColors(): Flow<List<Color>>
    suspend fun sync()

}