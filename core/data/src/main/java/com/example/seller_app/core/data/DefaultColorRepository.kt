package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.model.Color

class DefaultColorRepository(
    private val colorDao: ColorDao

): ColorRepository {

    override suspend fun getAllColors(): List<String> {
        return colorDao.getAllColors().map { it.name }
    }

    override suspend fun addColor(color: Color) {
        colorDao.addColor(color.asEntity())
    }
}