package com.example.seller_app.core.data

import com.example.seller_app.core.data.model.asEntity
import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.model.ProductColor

class DefaultColorRepository(
    private val colorDao: ColorDao

): ColorRepository {

    override suspend fun getAllColors(): List<String> {
        return colorDao.getAllColors().map { it.value }
    }

    override suspend fun addColor(color: ProductColor) {
        colorDao.addColor(color.asEntity())
    }
}