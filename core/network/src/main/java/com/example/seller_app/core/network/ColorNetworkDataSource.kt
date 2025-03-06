package com.example.seller_app.core.network

import com.example.seller_app.core.network.datasources.ColorRemoteDataSource
import com.example.seller_app.core.network.model.response.product.ColorDtoRes
import com.example.seller_app.core.network.retrofit.RetrofitAppNetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ColorNetworkDataSource(
    private val appNetworkApi: RetrofitAppNetworkApi
) : ColorRemoteDataSource {

    override suspend fun fetchColors(): Result<List<ColorDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = appNetworkApi.getColors()
                Result.success(request.data)
            } catch (e: HttpException) {
                Result.failure(Exception("Algo correu mal!"))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }
}