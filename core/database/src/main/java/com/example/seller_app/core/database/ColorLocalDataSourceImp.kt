package com.example.seller_app.core.database

import com.example.seller_app.core.database.dao.ColorDao
import com.example.seller_app.core.database.datasources.ColorLocalDataSource
import com.example.seller_app.core.database.model.ColorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ColorLocalDataSourceImp(
    private val colorDao: ColorDao
) : ColorLocalDataSource {

    override fun getColorsFlow(): Flow<List<ColorEntity>> {
        return colorDao.getAllColorsFlow()
    }


    override suspend fun upsertColors(colors: List<ColorEntity>) {
        withContext(Dispatchers.IO) {
            colorDao.insertColors(colors)
        }
    }

    override suspend fun deleteColorsNotIn(colors: List<String>) {
        withContext(Dispatchers.IO) {
            colorDao.deleteColorsNotIn(colors)
        }
    }

}