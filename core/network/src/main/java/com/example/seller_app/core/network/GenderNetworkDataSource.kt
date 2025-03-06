package com.example.seller_app.core.network

import com.example.seller_app.core.network.datasources.GenderRemoteDataSource
import com.example.seller_app.core.network.model.response.GenderDtoRes
import com.example.seller_app.core.network.retrofit.RetrofitAppNetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class GenderNetworkDataSource(
    private val appNetworkApi: RetrofitAppNetworkApi
): GenderRemoteDataSource {

    override suspend fun fetchGenders(): Result<List<GenderDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = appNetworkApi.getGenders()
                Result.success(response.data)
            }catch (e: HttpException) {
                Result.failure(Exception("Algo correu mal!"))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }

}