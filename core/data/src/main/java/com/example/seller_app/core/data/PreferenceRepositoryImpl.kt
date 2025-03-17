package com.example.seller_app.core.data

import com.example.datastore.PreferencesDataSource
import com.example.seller_app.core.data.repositories.PreferenceRepository

class PreferenceRepositoryImpl(
    private val preferenceDataSource: PreferencesDataSource
): PreferenceRepository {

    override suspend fun getLastUpdated(): Long? {
        return preferenceDataSource.getLatUpdated()
    }

    override suspend fun updateLastUpdated(timestamp: Long) {
        preferenceDataSource.updateLastUpdated(timestamp)
    }
}