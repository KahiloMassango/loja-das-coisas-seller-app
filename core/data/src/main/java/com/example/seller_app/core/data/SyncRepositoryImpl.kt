package com.example.seller_app.core.data

import com.example.seller_app.core.data.repositories.SyncRepository
import com.example.seller_app.core.network.datasources.SyncNetworkDatasource
import com.example.seller_app.core.network.model.response.SyncMetadataDtoRes

class SyncRepositoryImpl(
   private val syncNetworkDatasource: SyncNetworkDatasource,
): SyncRepository {

    override suspend fun getLastUpdated(): Result<Long> {
        return syncNetworkDatasource.getLastUpdate()
    }

    override suspend fun getSyncData(): Result<SyncMetadataDtoRes> {
        return syncNetworkDatasource.sync()
    }

}