package com.example.seller_app.core.network.datasources

import com.example.seller_app.core.network.model.response.SyncMetadataDtoRes

interface SyncNetworkDatasource {
    suspend fun getLastUpdate(): Result<Long>
    suspend fun sync(): Result<SyncMetadataDtoRes>

}