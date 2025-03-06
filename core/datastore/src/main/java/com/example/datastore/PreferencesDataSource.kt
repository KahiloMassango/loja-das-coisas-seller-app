package com.example.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first

class PreferencesDataSource(
    private val datastore: DataStore<Preferences>
) {
    private val CATEGORY_LAST_UPDATED = longPreferencesKey("category_last_updated")
    private val COLORS_LAST_UPDATED = longPreferencesKey("product_last_updated")
    private val SIZE_LAST_UPDATED = longPreferencesKey("size_last_updated")
    private val GENDER_LAST_UPDATED = longPreferencesKey("size_last_updated")

    suspend fun getCategoryLastUpdated(): Long? {
        return datastore.data.first()[CATEGORY_LAST_UPDATED]
    }
    suspend fun getColorsLastUpdated(): Long? {
        return datastore.data.first()[COLORS_LAST_UPDATED]
    }
    suspend fun getSizeLastUpdated(): Long? {
        return datastore.data.first()[SIZE_LAST_UPDATED]
    }
    suspend fun getGenderLastUpdated(): Long? {
        return datastore.data.first()[GENDER_LAST_UPDATED]
    }

    suspend fun updateCategoryLastUpdated(timestamp: Long) {
        datastore.edit { settings ->
            settings[CATEGORY_LAST_UPDATED] = timestamp
        }
    }
    suspend fun updateColorsLastUpdated(timestamp: Long) {
        datastore.edit { settings ->
            settings[COLORS_LAST_UPDATED] = timestamp
        }
    }
    suspend fun updateSizeLastUpdated(timestamp: Long) {
        datastore.edit { settings ->
            settings[SIZE_LAST_UPDATED] = timestamp
        }
    }

    suspend fun updateGenderLastUpdated(timestamp: Long) {
        datastore.edit { settings ->
            settings[GENDER_LAST_UPDATED] = timestamp
        }
    }

}