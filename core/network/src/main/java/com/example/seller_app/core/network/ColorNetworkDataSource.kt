package com.example.seller_app.core.network

import com.example.seller_app.core.network.common.extractErrorMessage
import com.example.seller_app.core.network.datasources.ColorRemoteDataSource
import com.example.seller_app.core.network.model.response.product.ColorDtoRes
import com.example.seller_app.core.network.retrofit.AppApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ColorNetworkDataSource(
    private val appNetworkApi: AppApiService
) : ColorRemoteDataSource {

    override suspend fun fetchColors(): Result<List<ColorDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = appNetworkApi.getColors().data
                Result.success(request)
            } catch (e: HttpException) {
                Result.failure(Exception(extractErrorMessage(e)))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }
}