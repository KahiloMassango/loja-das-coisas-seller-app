package com.example.seller_app.core.network

import com.example.seller_app.core.network.datasources.CategoryRemoteDataSource
import com.example.seller_app.core.network.model.response.product.CategoryDtoRes
import com.example.seller_app.core.network.retrofit.RetrofitAppNetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CategoryNetworkDataSource(
    private val appNetworkApi: RetrofitAppNetworkApi
) : CategoryRemoteDataSource {

    override suspend fun fetchCategories(): Result<List<CategoryDtoRes>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = appNetworkApi.getCategories()
                Result.success(response.data)
            } catch (e: HttpException) {
                Result.failure(Exception("Algo correu mal!"))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }
}