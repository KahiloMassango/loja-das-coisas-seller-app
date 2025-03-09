package com.example.seller_app.core.network

import com.example.seller_app.core.network.common.extractErrorMessage
import com.example.seller_app.core.network.datasources.OrderNetworkDataSource
import com.example.seller_app.core.network.model.response.order.OrderDetailDtoRes
import com.example.seller_app.core.network.model.response.order.OrdersDtoRes
import com.example.seller_app.core.network.retrofit.AppApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class OrderNetworkDataSourceImp(
    private val appApiService: AppApiService
): OrderNetworkDataSource {

    override suspend fun getOrders(): Result<OrdersDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = appApiService.getOrder()
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }

    override suspend fun getOrderById(id: String): Result<OrderDetailDtoRes> {
        return withContext(Dispatchers.IO) {
            try {
                val response = appApiService.getOrderById(id)
                Result.success(response.data)
            } catch (e: HttpException) {
                val message = extractErrorMessage(e)
                Result.failure(Exception(message))
            } catch (e: IOException) {
                Result.failure(IOException("Verifique sua conexão de internet"))
            }
        }
    }
}