package com.example.seller_app.core.network

import android.util.Log
import com.example.seller_app.core.network.common.extractErrorMessage
import com.example.seller_app.core.network.datasources.SizeRemoteDataSource
import com.example.seller_app.core.network.model.response.product.SizeDtoRes
import com.example.seller_app.core.network.retrofit.AppApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class SizeNetworkDataSource(
    private val appNetworkApi: AppApiService
) : SizeRemoteDataSource {

    override suspend fun fetchSizes(): Result<List<SizeDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = appNetworkApi.getSizes().data
                Result.success(response)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }
}