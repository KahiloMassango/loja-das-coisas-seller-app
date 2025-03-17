package com.example.seller_app.core.data.repositories

import com.example.seller_app.core.network.model.response.SyncMetadataDtoRes

interface SyncRepository {
    suspend fun getLastUpdated(): Result<Long>

    suspend fun getSyncData(): Result<SyncMetadataDtoRes>
}