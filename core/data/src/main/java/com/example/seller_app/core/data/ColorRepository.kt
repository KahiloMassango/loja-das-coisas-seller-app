package com.example.seller_app.core.data

import com.example.seller_app.core.model.Color

interface ColorRepository {

    suspend fun getAllColors(): List<String>
    suspend fun addColor(color: Color)

}