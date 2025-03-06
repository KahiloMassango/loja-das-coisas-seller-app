package com.example.seller_app.core.data.repositories

interface PreferenceRepository {
    suspend fun getCategoriesLastUpdated(): Long?
    suspend fun getColorsLastUpdated(): Long?
    suspend fun getSizesLastUpdated(): Long?
    suspend fun getGenderLastUpdated(): Long?

    suspend fun updateCategoriesLastUpdated(timestamp: Long)
    suspend fun updateColorsLastUpdated(timestamp: Long)
    suspend fun updateSizesLastUpdated(timestamp: Long)
    suspend fun updateGenderLastUpdated(timestamp: Long)


}