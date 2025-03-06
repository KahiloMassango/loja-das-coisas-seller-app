package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.model.Gender
import kotlinx.coroutines.flow.Flow

interface GenderRepository {
    fun getGenders(): Flow<List<Gender>>
    suspend fun sync()
}