package com.example.seller_app.core.network

import com.example.seller_app.core.network.common.extractErrorMessage
import com.example.seller_app.core.network.datasources.SyncNetworkDatasource
import com.example.seller_app.core.network.model.response.SyncMetadataDtoRes
import com.example.seller_app.core.network.retrofit.PublicApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class SyncNetworkDatasourceImpl(
    private val publicApiService: PublicApiService
): SyncNetworkDatasource {

    override suspend fun getLastUpdate(): Result<Long> {
        return withContext(Dispatchers.IO) {
            try {
                val request = publicApiService.fetchLastUpdated()
                Result.success(request.data.lastUpdated)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

    override suspend fun sync(): Result<SyncMetadataDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val request = publicApiService.fetchSyncMetadata()
                Result.success(request.data)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet", null))
            }
        }
    }

}