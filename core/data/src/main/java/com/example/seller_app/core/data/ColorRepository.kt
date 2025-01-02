package com.example.seller_app.core.data

import com.example.seller_app.core.model.ProductColor
import kotlinx.coroutines.flow.Flow

interface ColorRepository {

    suspend fun getAllColors(): List<String>
    suspend fun addColor(color: ProductColor)

}