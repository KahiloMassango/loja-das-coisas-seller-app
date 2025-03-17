package com.example.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first

class PreferencesDataSource(
    private val datastore: DataStore<Preferences>
) {
    private val LAST_SYNC = longPreferencesKey("last_sync")


    suspend fun getLatUpdated(): Long? {
        return datastore.data.first()[LAST_SYNC]
    }


    suspend fun updateLastUpdated(timestamp: Long) {
        datastore.edit { settings ->
            settings[LAST_SYNC] = timestamp
        }
    }


}