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
            val newColorsIds = colors.map { it.id }
            colorDao.insertColors(colors)
            colorDao.deleteColorsNotIn(newColorsIds)

        }
    }


}