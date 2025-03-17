package com.example.seller_app.core.data.repositories

interface PreferenceRepository {
    suspend fun getLastUpdated(): Long?
    suspend fun updateLastUpdated(timestamp: Long)
}