package com.example.seller_app.core.network

import com.example.seller_app.core.network.datasources.SizeRemoteDataSource
import com.example.seller_app.core.network.model.response.product.SizeDtoRes
import com.example.seller_app.core.network.retrofit.RetrofitAppNetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class SizeNetworkDataSource(
    private val appNetworkApi: RetrofitAppNetworkApi
) : SizeRemoteDataSource {

    override suspend fun fetchSizes(): Result<List<SizeDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = appNetworkApi.getSizes()
                Result.success(response.data)
            } catch (e: HttpException) {
                Result.failure(Exception("Algo correu mal"))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }
}