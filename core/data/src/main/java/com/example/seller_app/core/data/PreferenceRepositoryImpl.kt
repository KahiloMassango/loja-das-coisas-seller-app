package com.example.seller_app.core.data

import com.example.datastore.PreferencesDataSource
import com.example.seller_app.core.data.repositories.PreferenceRepository

class PreferenceRepositoryImpl(
    private val preferenceDataSource: PreferencesDataSource
): PreferenceRepository {

    override suspend fun getCategoriesLastUpdated(): Long? {
        return preferenceDataSource.getCategoryLastUpdated()
    }

    override suspend fun getColorsLastUpdated(): Long? {
        return preferenceDataSource.getColorsLastUpdated()
    }

    override suspend fun getSizesLastUpdated(): Long? {
        return preferenceDataSource.getSizeLastUpdated()
    }

    override suspend fun getGenderLastUpdated(): Long? {
        return preferenceDataSource.getGenderLastUpdated()
    }

    override suspend fun updateCategoriesLastUpdated(timestamp: Long) {
        preferenceDataSource.updateCategoryLastUpdated(timestamp)
    }

    override suspend fun updateColorsLastUpdated(timestamp: Long) {
        preferenceDataSource.updateColorsLastUpdated(timestamp)
    }

    override suspend fun updateSizesLastUpdated(timestamp: Long) {
        preferenceDataSource.updateSizeLastUpdated(timestamp)
    }

    override suspend fun updateGenderLastUpdated(timestamp: Long) {
        preferenceDataSource.updateGenderLastUpdated(timestamp)
    }
}